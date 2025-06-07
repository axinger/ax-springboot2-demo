package com.github.axinger.controller;

import com.github.axinger.server.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private StockServiceImpl stockService;

    @GetMapping("/pushClient")
    public String test(String userId, String message) {
        boolean pushed = stockService.pushToClient(userId, message);
        if (pushed) {
            return "推送成功";
        } else {
            return "推送失败";
        }
    }
}
