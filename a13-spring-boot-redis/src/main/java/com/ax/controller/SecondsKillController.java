package com.ax.controller;

import com.ax.service.SecondsKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SecondsKillController {

    /**
     * 1.库存-1
     * 2. 成功清单+1
     */


    @Autowired
    SecondsKillService secondsKillService;

    @RequestMapping(value = "/doSecKill")
    public boolean doSecKill() {

        Integer userId = new Random().nextInt(500);
        Integer prodId = 101;

        return secondsKillService.doSecKill(userId, prodId);
    }

    @RequestMapping(value = "/secKill")
    public Object doSecKill2() {
        Integer prodId = 101;
        secondsKillService.createProductCount(prodId);
        ExecutorService service = Executors.newFixedThreadPool(50);
        try {
            for (int i = 0; i < 100; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " :办理业务");
                    Integer userId = new Random().nextInt(500);
                    boolean secKill = secondsKillService.doSecKill(userId, prodId);
                    System.out.println("秒杀 secKill = " + secKill);
                });
            }
        } finally {
            /// 关闭
            service.shutdown();
        }
        Integer count = secondsKillService.productCount(prodId);

        Map map = new HashMap();
        map.put("count", count);

        return map;
    }


    @RequestMapping(value = "/doSecKill3")
    public boolean doSecKill_2() {

        Integer userId = new Random().nextInt(40);
        Integer prodId = 101;

        return secondsKillService.doSecKillOfRedissonLock(userId, prodId);
    }


}
