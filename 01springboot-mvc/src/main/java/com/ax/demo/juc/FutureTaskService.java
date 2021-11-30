package com.ax.demo.juc;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface FutureTaskService {

    void testAsync1();

    Future<String> asyncGetResult1();

    Future<String> asyncGetResult2();

    CompletableFuture<String> asyncTes1();

    CompletableFuture<String> asyncTes2();

    CompletableFuture<Map> asyncTes1And2();

    Map asyncTes1And2_2() throws ExecutionException, InterruptedException;

}
