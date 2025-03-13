package com.github.axinger.controller;

import com.github.axinger.model.bean.ApplicationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
    @Autowired
    private ApplicationInfo applicationInfo;

    @GetMapping("/env")
    public Object test2() {
        return applicationInfo;
    }
}
