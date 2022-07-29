package com.ax.future.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
@Slf4j
class FutureTaskServiceTest {


    @Autowired
    @Qualifier("orderExecutor")
    private Executor orderExecutor;

    @Autowired
    @Qualifier("smsExecutor")
    private Executor smsExecutor;

    @Autowired
    private Executor executor;

    @Async("cExecutor")
    public void method1(long timeout, String name) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
            log.info("{}执行任务完成,Thread.currentThread() = {}", name, Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Async
    void test1() {

        List<CompletableFuture> futureList = new ArrayList<>();

        AtomicReference<String> orderNo = new AtomicReference<>("还没有订单");


        StopWatch watch = new StopWatch();
        watch.start();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            method1(1, "订单");
            orderNo.set("订单号001");
            return "返回内容1";
        }, orderExecutor);

        futureList.add(future1);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            method1(2, "短信");
            return "返回内容2";
        }, smsExecutor);

        futureList.add(future2);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            method1(2, "通用");
            return "返回内容3";
        }, executor);

        futureList.add(future3);

        future1.whenComplete((val, error) -> {
            if (error != null) {
                System.out.println("error = " + error);
            } else {
                System.out.println("val = " + val);
            }
        });

        log.info("主线程任务不阻塞 ======= {}", Thread.currentThread().getName());
        final CompletableFuture[] array = futureList.toArray(new CompletableFuture[futureList.size()]);

        final CompletableFuture<Void> all = CompletableFuture.allOf(array);

        CompletableFuture.anyOf(array).whenComplete((val, error) -> {
            if (error != null) {
                System.out.println("anyOf error = " + error);
            } else {
                System.out.println("anyOf val = " + val);
            }
        });


        all.whenComplete((val, error) -> {
            if (error != null) {
                System.out.println("all error = " + error);
            } else {
                System.out.println("all val = " + val);
            }
        });


        System.out.println("all.join前,订单值 = " + orderNo.get());
        all.join();
        System.out.println("all.join后,订单值 = " + orderNo.get());

        log.info("future1.join() = " + future1.join());
        log.info("future2.join() = " + future2.join());

        watch.stop();
        log.info("getTotalTimeSeconds = " + watch.getTotalTimeSeconds());

//        future1.complete("aaa");
//
//        System.out.println("future1.join() = " + future1.join());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    void test2() {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info(" Thread.currentThread().getName() = {}", Thread.currentThread().getName());
            return "返回内容1";
        }, orderExecutor);
    }


    @Test
    void test_3() {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info(" Thread.currentThread().getName() = {}", Thread.currentThread().getName());
            return "返回内容1";
        });
    }

    @Test
    @Async
    void test_4() {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info(" Thread.currentThread().getName() = {}", Thread.currentThread().getName());
            return "返回内容1";
        });
    }


    @Test
    void test_5() {

        CompletableFuture.supplyAsync(() -> {
            log.info(" Thread.currentThread().getName() = {}", Thread.currentThread().getName());
            int i = 1 / 0;
            return 1;
        }, executor).exceptionally(throwable ->
        {
            System.out.println("throwable = " + throwable);
            return -1;
        }).thenApply(val -> {
            log.info("thenApply val = " + val);
            return val == -1 ? -2 : val;
        }).whenComplete((val, error) -> {
            log.info("whenComplete val = " + val);
            log.info("error = " + error);
        }).thenAcceptAsync(val -> { // 启用另一个线程池
            log.info("thenAcceptAsync = {}", val);
        }, orderExecutor);
    }


    @Test
    void test_6() {
        // 不拦截 exceptionally,whenComplete 才有error,
        // whenComplete有error,不会执行后面的 thenXXX
        CompletableFuture.supplyAsync(() -> {
            log.info(" Thread.currentThread().getName() = {}", Thread.currentThread().getName());
//            int i = 1 / 0;
            return 1;
        }, executor).whenComplete((val, error) -> {
            log.info("whenComplete val = " + val);
            log.info("error = " + error);
        }).thenAcceptAsync(val -> { // 启用另一个线程池
            log.info("thenAcceptAsync = {}", val);
        }, orderExecutor);
    }
}
