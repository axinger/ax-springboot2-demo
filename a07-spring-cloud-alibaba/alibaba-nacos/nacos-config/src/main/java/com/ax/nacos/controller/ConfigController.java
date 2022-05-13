package com.ax.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName ConfigController.java
 * @Description TODO
 * @createTime 2021年12月16日 20:21:00
 */

@RestController
@RefreshScope // 支持nacos的动态刷新功能
public class ConfigController {

    @Value("${config.info:#{null}}")
    private String info;

    @Value("${config.name:#{null}}")
    private String name;

    @GetMapping("/")
    public Object getInfo() {
        System.out.println("getInfo = " + info);
        Map map = new HashMap(16);
        map.put("info", info);
        map.put("name", name);
        return map;
    }
}
