package com.ax.demo.controller;

import com.ax.demo.juc.FutureTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class JucController {
    @Autowired
    FutureTaskService taskService;


    @RequestMapping("/async")
    public Object async() throws InterruptedException, ExecutionException {

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
        return map;
    }


    @RequestMapping("/async2")
    public Object test2() throws ExecutionException, InterruptedException {

        Future<String> r1 = taskService.asyncTes1();
        Future<String> r2 = taskService.asyncTes2();
        Map map = new HashMap();
        map.put("r1", r1.get());
        map.put("r2", r2.get());

        return map;
    }

    @RequestMapping("/async3")
    public Object test3() throws ExecutionException, InterruptedException {
        return taskService.asyncTes1And2().get();
    }

    @RequestMapping("/async31")
    public Object test3_1() throws ExecutionException, InterruptedException {
        return taskService.asyncTes1And2_2();
    }


    @RequestMapping("/async4")
    public Object test4() throws ExecutionException, InterruptedException {
        return taskService.asyncTes1And2().join();
    }
}
