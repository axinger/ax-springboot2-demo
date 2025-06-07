package com.github.axinger.controller;

import com.github.axinger.server.SimpleGrpcSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SimpleGrpcSimpleService simpleGrpcSimpleService;

    @GetMapping("/pushClient")
    public String test(String message) {
        boolean pushed = simpleGrpcSimpleService.pushToClient("a21-grpc-client", message);
        if (pushed) {
            return "推送成功";
        } else {
            return "推送失败";
        }
    }
}
