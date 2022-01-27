package com.ax.juc.service.impl;

import com.ax.juc.service.FutureTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Service
@Slf4j
public class FutureTaskServiceImpl implements FutureTaskService {

    // AsyncResult 是spring的类型
    @Async("smsExecutor")
    @Override
    public void testAsync1() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("异步 无返回");
    }


    @Async("smsExecutor")
    @Override
    public Future<String> asyncGetResult1() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("异步1 返回结果A");
        return new AsyncResult<String>("结果A");
    }

    @Async("smsExecutor")
    @Override
    public Future<String> asyncGetResult2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("异步2 返回结果B");
        return new AsyncResult<String>("结果B");
    }

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Async("smsExecutor")
    @Override
    public CompletableFuture<String> asyncTes1(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncTes1...........");
        return CompletableFuture.completedFuture("asyncTes1");
    }


    @Async("smsExecutor")
    @Override
    public CompletableFuture<String> asyncTes2(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncTes2...........");
        return CompletableFuture.completedFuture("asyncTes2");
    }

    @Async("smsExecutor")
    @Override
    public CompletableFuture<String> asyncTes3(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncTes2...........");
        return CompletableFuture.completedFuture("asyncTes3");
    }


    @Async("smsExecutor")
    @Override
    public CompletableFuture<Map> asyncTes1And2() {

//        CompletableFuture<String> all = CompletableFuture.allOf(asyncTes1(), asyncTes2());


        CompletableFuture<Map> future3 = asyncTes1(2).thenCombine(asyncTes2(3), (r1, r2) -> {
            Map map = new HashMap();
            map.put("result1", r1);
            map.put("result2", r2);
            return map;
        });
        System.out.println("asyncTes1And2 result: " + future3.join());


        return future3;

    }

//    @Async("smsExecutor")
    @Override
    public Object completableAll() {

        Map map = new HashMap();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 = 完成");
            return "future1 finished!";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 = 完成");
            return "future2 finished!";
        });
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);

        allOf.thenAccept(v -> {
            System.out.println("thenAccept = " + v);
        });

        allOf.thenRun(()->{
            System.out.println("thenRun 完成");
        });

        allOf.whenComplete((k, v) -> {

            System.out.println("whenComplete k = " + k);
            System.out.println("whenComplete v = " + v);
            try {
                System.out.println("allOf.get() = " + allOf.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        future1.whenComplete((v, k) -> {
            map.put("future1", v);
        });
        future2.whenComplete((v, k) -> {
            map.put("future2", v);
        });
        System.out.println("future1: " + future1.isDone() + " future2: " + future2.isDone());

        //join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
        allOf.join();

        System.out.println("map = " + map);
        return map;
    }

    @Override
    public Map asyncTes1And2_2() throws ExecutionException, InterruptedException {

        CompletableFuture<Map> future3 = asyncTes1(2).thenCombine(asyncTes2(3), (r1, r2) -> {
            Map map = new HashMap();
            map.put("result1", r1);
            map.put("result2", r2);
            return map;
        });


        System.out.println("asyncTes1And2_2: " + future3.join());
        /// 使用CompletableFuture.allOf实现异步执行同步搜集结果
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(asyncTes1(), asyncTes2());


        return future3.get();
    }

}
