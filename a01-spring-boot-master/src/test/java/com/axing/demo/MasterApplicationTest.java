package com.axing.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
class MasterApplicationTest {

    @Test
    void test1() {
        final String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
        log.error("error = {}", date);
        log.info("info = {}", date);
        log.debug("debug = {}", date);
        log.warn("info = {}", date);
    }
}
