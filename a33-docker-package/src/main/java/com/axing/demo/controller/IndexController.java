package com.axing.demo.controller;

import com.axing.common.response.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class IndexController {

    @Value("${config.name}")
    private String name;

    @GetMapping("/data")
    public Result<?> data() {
        log.info("info");
        log.error("error");
        log.warn("warn");
        log.debug("debug");
        log.trace("trace");
        return Result.ok(Map.of("name", name, "dateTime", LocalDateTime.now()));
    }


    @GetMapping("/")
    public Result<?> index() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", 14);
        map.put("dateTime", LocalDateTime.now());

        return Result.ok(map);
    }
}
