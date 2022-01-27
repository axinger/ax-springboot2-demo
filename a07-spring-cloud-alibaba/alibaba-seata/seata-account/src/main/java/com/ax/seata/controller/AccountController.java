package com.ax.seata.controller;

import com.ax.seata.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 */
@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/test")
    public Object test() {
        return "test-AccountController";
    }

    /**
     * 减少库存
     * */
    @GetMapping(value = "/account/decrease")
    Object decrease(@RequestParam(value = "userId") Long userId,
                    @RequestParam(value = "money") BigDecimal money ){
        System.out.println("接收到账户请求============="+userId);
        return accountService.decrease(userId,money);
    }

}
