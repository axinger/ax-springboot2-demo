package com.ax.demo.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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


    @Async("smsExecutor")
    @Override
    public CompletableFuture<String> asyncTes1() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncTes1...........");
        return CompletableFuture.completedFuture("asyncTes1");
    }


    @Async("smsExecutor")
    @Override
    public CompletableFuture<String> asyncTes2() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncTes2...........");
        return CompletableFuture.completedFuture("asyncTes2");
    }


    @Async("smsExecutor")
    @Override
    public CompletableFuture<Map> asyncTes1And2() {

//        CompletableFuture<String> all = CompletableFuture.allOf(asyncTes1(), asyncTes2());


        CompletableFuture<Map> future3 = asyncTes1().thenCombine(asyncTes2(), (r1, r2) -> {
            Map map = new HashMap();
            map.put("result1", r1);
            map.put("result2", r2);
            return map;
        });
        System.out.println("asyncTes1And2 result: " + future3.join());


        return future3;

    }

    @Override
    public Map asyncTes1And2_2() throws ExecutionException, InterruptedException {

        CompletableFuture<Map> future3 = asyncTes1().thenCombine(asyncTes2(), (r1, r2) -> {
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