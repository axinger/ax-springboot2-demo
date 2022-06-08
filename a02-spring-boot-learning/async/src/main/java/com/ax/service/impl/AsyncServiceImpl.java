package com.ax.service.impl;

import com.ax.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName AsyncServiceImpl.java
 * @description TODO
 * @createTime 2022年06月08日 10:20:00
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {


    @Autowired
    ApplicationContext applicationContext;

    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    @Override
    public void test1() {
        System.out.println("test1 currentThread = " + Thread.currentThread().getName());
    }


//    @Autowired
//    AsyncService asyncService;

    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    @Override
    public void test2() {
        System.out.println("test1 currentThread = " + Thread.currentThread().getName());
    }

    /**
     * 嵌套
     */
    @Async
    @Override
    public void test3() {
        System.out.println("test3 currentThread = " + Thread.currentThread().getName());
//        test4();
//        this.test4();;
//        System.out.println("((AsyncService)AopContext.currentProxy()) = " + (AopContext.currentProxy()));

        /**
         * 和事务效果一样,使用了aop代理,所以内部调用
         * */
        applicationContext.getBean(AsyncService.class).test4();

    }

    @Async
    @Override
    public void test4() {
        System.out.println("test4 currentThread = " + Thread.currentThread().getName());
    }

    @Autowired
    private Executor executor;

    //    @Async("customizeThreadPool")
    @Async
    @Override
    public void test11() {
        log.info("test11");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
//            method2(2, "通用");
            log.info("传参 executor");
            return "返回内容3";
        }, executor);

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
//            method2(2, "通用");
            log.info("不传参 executor");
            return "返回内容3";
        });

        int sleepSeconds = new Random().nextInt(3) + 1;
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
