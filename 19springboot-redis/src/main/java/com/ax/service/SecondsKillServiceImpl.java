package com.ax.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SecondsKillServiceImpl implements SecondsKillService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private Redisson redisson;

    String prodKey(Integer prodId) {
        // 库存key
        String prodKey = "sk:" + prodId + ":qt";
        return prodKey;
    }

    String userKilledKey(Integer prodId) {
        return "sk:" + prodId + ":user";
    }

    @Override
    public void createProductCount(Integer prodId) {
        String prodKey = prodKey(prodId);
        redisTemplate.opsForValue().set(prodKey, "10");
        Integer count = productCount(prodId);
        log.info("初始化库存 = {}", count);
    }

    @Override
    public Integer productCount(Integer prodId) {
        String prodKey = prodKey(prodId);
        return (Integer) redisTemplate.opsForValue().get(prodKey);
    }

    /**
     * 秒杀,并发
     */
    @Override
    public boolean doSecKill(Integer userId, Integer prodId) {
        // 库存key
        String prodKey = prodKey(prodId);
        log.info("库存key = " + prodKey);
        // 商品已经秒杀过的 用户集合key
        String userKilledKey = userKilledKey(prodId);
        log.info("用户集合key  = " + userKilledKey);

        /// 监视库存,并发
        redisTemplate.watch(prodKey);
        //判断库存是否存在
        if (redisTemplate.opsForValue().get(prodKey) == null) {
            log.info("秒杀还未开始.........");
            return false;
        }

        // 判断用户
        Boolean member = redisTemplate.opsForSet().isMember(userKilledKey, userId);
        if (member) {
            log.info("已经秒杀过了.........");
            return false;
        }

        // 判断库存
        if ((Integer) redisTemplate.opsForValue().get(prodKey) <= 0) {

            log.info("库存为0,活动结束.........");
            return false;
        }
        // 事务,组队
//        redisTemplate.multi();
//        redisTemplate.opsForValue().decrement(prodKey);
//        redisTemplate.opsForSet().add(userKilledKey, userId);
//        // 事务,执行
//        List<Object> exec = redisTemplate.exec();
////        redisTemplate.unwatch();
//        if (exec == null || exec.size()==0){
//            log.info("秒杀失败........."+userId);
//            return false;
//        }


        redisTemplate.execute((RedisCallback<Object>) (redisOperations) -> {

            // 开启事务支持，在同一个 Connection 中执行命令
            redisTemplate.setEnableTransactionSupport(true);
            redisOperations.multi();
            redisTemplate.opsForValue().decrement(prodKey);
            redisTemplate.opsForSet().add(userKilledKey, userId.toString());
            return redisOperations.exec();
        });


        log.info("秒杀成功一次........." + userId);
        return true;
    }

    @Override
    public boolean doSecKillOfRedissonLock(Integer userId, Integer prodId) {

        RLock phoneLock = redisson.getLock("phoneLock");
        phoneLock.lock(3, TimeUnit.SECONDS);
        try {

            // 库存key
            String prodKey = prodKey(prodId);
            // 商品已经秒杀过的 用户集合key
            String userKilledKey = userKilledKey(prodId);
            //判断库存是否存在
            if (redisTemplate.opsForValue().get(prodKey) == null) {
                log.info("秒杀还未开始.........");
                return false;
            }
            Integer count = (Integer) redisTemplate.opsForValue().get(prodKey);
            // 判断库存
            if (count <= 0) {
                log.info("库存为0,活动结束.........");
                return false;
            }

            // 判断用户
            Boolean member = redisTemplate.opsForSet().isMember(userKilledKey, userId);
            if (member) {
                log.info("已经秒杀过了.........");
                return false;
            }

//            redisTemplate.opsForValue().set("phone", String.valueOf(count - 1));

            redisTemplate.opsForValue().decrement(prodKey);
            redisTemplate.opsForSet().add(userKilledKey, userId.toString());
            System.out.println(" userId = " + userId + "抢到了" + count + "号商品");
            return true;
        } finally {
            phoneLock.unlock();
        }
    }
}
