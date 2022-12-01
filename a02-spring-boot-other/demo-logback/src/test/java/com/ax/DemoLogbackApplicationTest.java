package com.ax;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@SpringBootTest
@Slf4j
class DemoLogbackApplicationTest {

    @Test
    void test1() {
        final String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
        log.error("error = {}", date);
        log.info("info = {}", date);
        log.debug("debug = {}", date);
        log.warn("info = {}", date);
    }
}