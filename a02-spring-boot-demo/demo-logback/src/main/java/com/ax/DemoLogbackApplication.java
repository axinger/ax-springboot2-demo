package com.ax;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xing
 */
@SpringBootApplication
@Slf4j
public class DemoLogbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoLogbackApplication.class, args);
        final String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
        log.error("error = {}",date);
        log.info("info = {}",date);
        log.debug("debug = {}",date);
        log.warn("info = {}",date);
    }
}
