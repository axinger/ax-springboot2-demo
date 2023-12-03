package com.axing.demo.service.impl;

import com.axing.demo.handler.CustomRedissonLockExecutor;
import com.axing.demo.service.LockService;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LockServiceImpl implements LockService {

    @Autowired
    private LockTemplate lockTemplate;

    @Override
    public void lock(String resourceKey) {

        LockInfo lock = lockTemplate.lock(resourceKey, 10000L, 2000L, CustomRedissonLockExecutor.class);
        if (lock == null) {
            // 获取不到锁
            throw new RuntimeException("业务处理中，请稍后再试...");
        }
        // 获取锁成功，处理业务
        try {
            doBusiness();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lockTemplate.releaseLock(lock);
        }
    }

    private void doBusiness() {
        // TODO 业务执行逻辑
    }
}
