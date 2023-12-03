package com.axing.demo.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.axing.common.response.result.Result;
import com.axing.demo.model.User;
import com.baomidou.lock.annotation.Lock4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

    /**
     * 抢占不到锁，Lock4j会抛出com.baomidou.lock.exception.LockFailureException: request failed,please retry it.异常，通过全局异常处理返回
     */
    @GetMapping("/lockMethod")
    @Lock4j(keys = {"#key"}, acquireTimeout = 1000, expire = 10000)
    //在注解上直接指定特定的执行器：
    // @Lock4j(executor = CustomRedissonLockExecutor.class)。
    public Result<?> lockMethod(@RequestParam String key) {
        ThreadUtil.sleep(5000);
        return Result.ok(key);
    }
    //完全配置，支持spel
    @Lock4j(keys = {"#user.id", "#user.name"}, expire = 60000, acquireTimeout = 1000)
    public User customMethod(User user) {
        return user;
    }
}
