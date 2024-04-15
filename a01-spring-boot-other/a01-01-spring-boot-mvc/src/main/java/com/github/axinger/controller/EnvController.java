package com.github.axinger.controller;

import com.axing.demo.bean.ApplicationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
    @Autowired
    private ApplicationInfo applicationInfo;

    @GetMapping("/")
    public Object test2() {
        return applicationInfo;
    }
}
