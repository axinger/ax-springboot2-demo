package com.axing.demo.handler;

import com.baomidou.lock.executor.AbstractLockExecutor;
import org.springframework.stereotype.Component;

/**
 * 自定义分布式锁执行器
 *
 * @author: austin
 * @since: 2023/3/15 15:45
 */
@Component
public class CustomRedissonLockExecutor extends AbstractLockExecutor<String> {


    @Override
    public String acquire(String lockKey, String lockValue, long expire, long acquireTimeout) {
        return null;
    }

    @Override
    public boolean releaseLock(String key, String value, String lockInstance) {
        return false;
    }
}
