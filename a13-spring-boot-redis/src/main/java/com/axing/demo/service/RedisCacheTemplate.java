package com.axing.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisCacheTemplate {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key   redis的 key值
     * @param delta 自增的增量
     * @return
     * @Title: incr
     * @Description: 获取redis自增序号
     */
    public long incr(String key, long delta, long time) {
        try {
            long l = redisTemplate.opsForValue().increment(key, delta);
            if (time > 0) {
                expire(key, time);
            }
            return l;
        } catch (Exception e) {
            log.error("redis获取" + key + "失败", e);
            return -1;
        }
    }

    /**
     * @param key
     * @param time
     * @return
     * @Title: expire
     * @Description: 设置过期时间
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis设置" + key + "过期时间失败", e);
            return false;
        }
    }
}
