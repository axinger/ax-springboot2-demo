package com.ax.future.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class FutureTaskServiceTest {


    @Autowired
    @Qualifier("cExecutor")
    private Executor executor;


    @Test
    @Async
    void test1() {
//        System.out.println("executor = " + executor);

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread.currentThread() = " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("执行任务 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "All1";
        }, executor);

        future1.whenComplete((v1,v2)->{
            System.out.println("v1 = " + v1);
            System.out.println("v2 = " + v2);
        });

        System.out.println("future1.join() = " + future1.join());

        future1.complete("aaa");

        System.out.println("future1.join() = " + future1.join());

        try {
            TimeUnit.MINUTES.sleep(10);
            System.out.println("执行任务 1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
