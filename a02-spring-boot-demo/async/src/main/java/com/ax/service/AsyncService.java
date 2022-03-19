package com.ax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName AsyncService.java
 * @Description TODO
 * @createTime 2022年02月18日 17:35:00
 */

@Service
public class AsyncService {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    public void test1() {
        System.out.println("test1 currentThread = " + Thread.currentThread().getName());
    }


//    @Autowired
//    AsyncService asyncService;

    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    public void test2() {
        System.out.println("test1 currentThread = " + Thread.currentThread().getName());
    }

    /**
     * 嵌套
     */
    @Async
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
    public void test4() {
        System.out.println("test4 currentThread = " + Thread.currentThread().getName());
    }


    @Async("customizeThreadPool")
    public void test11() {
        int sleepSeconds = new Random().nextInt(3) + 1;
        try {
            Thread.sleep(sleepSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test11 currentThread = " + Thread.currentThread().getName());
    }
}
