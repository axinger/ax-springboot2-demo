package com.ax.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LockServiceImpl {

    @Autowired
    private RedisTemplate<String, Object> template;


    public void testLock() {
        // 分布式,防止误删锁
        String uuid = UUID.randomUUID().toString();

        //1 获取锁,和锁过期时间
        Boolean lock = template.opsForValue().setIfAbsent("lock", uuid, 2, TimeUnit.SECONDS);
        //2.获取锁成功,查询num的值
        if (lock) {
            Object num = template.opsForValue().get("num");
            //2.1判空
            if (StringUtils.isEmpty(num)) {

                return;
            }
            //2.2有值
            int num1 = Integer.parseInt(num + "");
            //2.3 吧redis的num +1
            template.opsForValue().set("num", ++num1);
            //2.4 释放锁
            // 判断uuid是否一样,方式误删
            String lockKey = (String) template.opsForValue().get("lock");
            if (lockKey.equals(uuid)) {
                template.delete("lock");
            }

        } else {

            try {
                Thread.sleep(1);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
