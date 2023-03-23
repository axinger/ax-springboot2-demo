package com.axing.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TestController.java
 * @description TODO
 * @createTime 2022年05月12日 11:52:00
 */

@RestController
public class TestController {

    @Value("${spring.profiles.port:8080}")
    Integer port;

    @Value("${spring.profiles.active}")
    String active;

    @Value("${spring.application.name:空}")
    String name;

    @Value("${spring.application.description}")
    String bootName;


    @GetMapping("/")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("time", LocalDateTime.now());
        map.put("port", port);
        map.put("active", active);
        map.put("name", name);
        map.put("bootName", bootName);
        return map;
    }
}
