package com.ax.consumer.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ax.demo.entity.TAccount;
import xyz.ax.demo.service.TAccountService;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName controller.java
 * @Description TODO
 * @createTime 2021年12月18日 21:55:00
 */

@RestController
public class TestController {

    @DubboReference(version = "1.0.0")
    TAccountService accountService;


    @GetMapping("/test")
    public Object test() {
        System.out.println("进入了ConsumerController.............");
        return "ConsumerController+test";
    }


    @GetMapping("/account")
    public TAccount account() {
        System.out.println("进入了ConsumerController.............");
        return accountService.selectByPrimaryKey(1L);
    }
}
