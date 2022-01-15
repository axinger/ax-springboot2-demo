package com.ax.controller;
/*
 * @auther 顶风少年
 * @mail dfsn19970313@foxmail.com
 * @date 2020-01-13 11:19
 * @notify
 * @version 1.0
 */

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 使用分布式锁,进行秒杀
 */
@RestController
public class RedissonLock {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private Redisson redisson;

    @GetMapping(value = "buy2")
    public String get() {
        RLock phoneLock = redisson.getLock("phoneLock");
        phoneLock.lock(3, TimeUnit.SECONDS);
        try {
            String phone = redisTemplate.opsForValue().get("phone");
            Integer count = Integer.valueOf(phone);
            if (count > 0) {
                redisTemplate.opsForValue().set("phone", String.valueOf(count - 1));
                System.out.println("抢到了" + count + "号商品");
            } else {
                System.out.println("库存没有了.............");
            }

        } finally {
            phoneLock.unlock();
        }
        return "";
    }
}

