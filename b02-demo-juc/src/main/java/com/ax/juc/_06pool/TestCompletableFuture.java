package com.ax.juc._06pool;

import java.util.concurrent.*;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TestCompletableFuture.java
 * @Description TODO
 * @createTime 2022年02月12日 21:42:00
 * https://www.jianshu.com/p/dff9063e1ab6
 */

public class TestCompletableFuture {
    public static void main(String[] args) {
        // runAsync 和 supplyAsync 方法的区别是runAsync返回的CompletableFuture是没有返回值的。
;
        test_supplyAsync();

    }

    static void test_runAsync(){
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello");
        });

        try {
            System.out.println("future.get() = " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("CompletableFuture");
    }

    static void test_supplyAsync(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("进入异步===============");
            System.out.println("当前线程：" + Thread.currentThread().getName());
            System.out.println("executorService 是否为守护线程 :" + Thread.currentThread().isDaemon());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello");
            return "Hello";
        }).thenApply(e ->e).whenComplete((k,v)->{

            System.out.println("whenComplete k = " + k+", v = "+v);
        });

        // 如果执行成功:
        future.thenAccept((result) -> {
            System.out.println("执行成功 = " + result);
        });
        // future.get()在等待执行结果时，程序会一直block，如果此时调用complete(T t)会立即执行。
//        future.complete("World");

        // future.get() 是阻塞式
//        try {
//            System.out.println("future.get() = " + future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        System.out.println("CompletableFuture");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void test_ExecutorCompletionService(){

        ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512),
                new ThreadPoolExecutor.DiscardPolicy());

        final ExecutorCompletionService service1 = new ExecutorCompletionService(executorService);
        try {
            final Object o = service1.take().get();
            System.out.println("service1 = " + o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        {

            final ExecutorCompletionService service2 = new ExecutorCompletionService(executorService);
            try {
                final Object o = service2.take().get();
                System.out.println("service2 = " + o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
