package com.axing.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class IndexController {

    @Value("${config.name}")
    private String name;

    @GetMapping("/")
    public Object index() {
        log.info("info");
        log.error("error");
        log.warn("warn");
        log.debug("debug");
        log.trace("trace");
        return Map.of("name", name);
    }
}
