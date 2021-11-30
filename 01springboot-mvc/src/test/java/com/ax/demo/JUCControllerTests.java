package com.ax.demo;

import com.ax.demo.controller.JUCController;
import com.ax.demo.juc.FutureTaskService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SpringBootTest
@Slf4j
public class JUCControllerTests {
    @Autowired
    FutureTaskService taskService;
    @Autowired
    private JUCController jucController;

    @Test
    void test1() {

        try {
            jucController.test2();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void test2() throws ExecutionException, InterruptedException {

        // 开始时间
        long stime = System.nanoTime();

        taskService.testAsync1();
        Future<String> r1 = taskService.asyncGetResult1();
        Future<String> r2 = taskService.asyncGetResult2();

        Map map = new HashMap();
        map.put("r1", r1.get());
        map.put("r2", r2.get());

        log.info("Controller执行完毕");
        // 结束时间
        long etime = System.currentTimeMillis();

        float seconds = (etime - stime) / 1000F;
        System.out.println("seconds = " + seconds);
        log.info("执行时长：{} 秒", seconds);
        System.out.println("map = " + map);


    }


    @Test
    void test3() throws ExecutionException, InterruptedException {

        // 开始时间
        long stime = System.nanoTime();

        taskService.testAsync1();
        Future<String> r1 = taskService.asyncTes1();
        Future<String> r2 = taskService.asyncTes2();

        Map map = new HashMap();
        map.put("r1", r1.get());
        map.put("r2", r2.get());

        log.info("Controller执行完毕");
        // 结束时间
        long etime = System.currentTimeMillis();

        float seconds = (etime - stime) / 1000F;
        System.out.println("seconds = " + seconds);
        log.info("执行时长：{} 秒", seconds);
        System.out.println("map = " + map);


    }

}


class java8Async {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // CompletableFuture.supplyAsync（），定义要执行的异步任务
        CompletableFuture<String> asyncResult = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                // TODO Auto-generated method stub
                try {
                    System.out.println("任务1");
                    // 睡眠1s
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("任务2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "返回数据async";
            }
        }, executor);

        // asyncResult.thenAccept(new Consumer<String>() ， 重写accept（）方法去定义回调函数
        asyncResult.thenAccept(new Consumer<String>() {
            public void accept(String arg0) {
                System.out.println("return =" + arg0);
            }
        });
        // return
        System.out.println("任务执行完成");
    }

}