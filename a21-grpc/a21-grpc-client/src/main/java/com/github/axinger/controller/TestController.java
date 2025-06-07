package com.github.axinger.controller;


import com.github.axinger.event.FromServletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private FromServletService fromServletService;

    @GetMapping("/pushServer")
    public String pushServer(String message) {
        boolean b = fromServletService.sendMessage(message);
        return b ? "成功" : "失败";
    }
}
