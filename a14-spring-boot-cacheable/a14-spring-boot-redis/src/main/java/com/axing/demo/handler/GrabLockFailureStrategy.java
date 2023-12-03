package com.axing.demo.handler;

import com.baomidou.lock.LockFailureStrategy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义抢占锁失败执行策略
 *
 * @author: austin
 * @since: 2023/3/15 15:49
 */
@Component
public class GrabLockFailureStrategy implements LockFailureStrategy {

    @Override
    public void onLockFailure(String key, Method method, Object[] arguments) {

    }
}
