package com.axing.demo27;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class A27SpringdocApplication {

    public static void main(String[] args) {
        SpringApplication.run(A27SpringdocApplication.class, args);
        log.debug("测试debug = {}", LocalDateTime.now());
        log.info("测试info = {}", LocalDateTime.now());
        log.warn("测试warn = {}", LocalDateTime.now());
        log.error("测试error = {}", LocalDateTime.now());
        log.error("测试error2 = {}", LocalDateTime.now());
        log.trace("测试trace = {}", LocalDateTime.now());
    }

}
