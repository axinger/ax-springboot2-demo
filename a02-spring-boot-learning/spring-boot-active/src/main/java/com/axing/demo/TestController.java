package com.axing.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Value("${server.port}")
    Integer port;

    @Value("${spring.profiles.active}")
    String active;

    @Value("${spring.application.name}")
    String name;

    @Value("${spring.application.boot-name:空}")
    String bootName;


    @GetMapping("/")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap();
        map.put("port", port);
        map.put("active", active);
        map.put("name", name);
        map.put("bootName", bootName);

        return map;
    }
}
