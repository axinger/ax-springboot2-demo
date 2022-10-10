package com.axing.demo.service.impl;

import com.axing.demo.service.RedissonLockService;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName RedissonLockServiceImpl.java
 * @Description TODO
 * @createTime 2022年02月22日 19:26:00
 */

public class RedissonLockServiceImpl implements RedissonLockService {

    // 1.可重入锁, AB方法, A调用B, 用同一个锁,避免死锁


    @Autowired
    private Redisson redisson;
}
