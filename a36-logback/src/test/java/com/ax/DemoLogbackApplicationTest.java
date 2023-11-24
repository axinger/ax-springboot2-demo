package com.ax;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class DemoLogbackApplicationTest {

    @Autowired
    private LoginService loginService;

    @Test
    void test1() {
//        final String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
//        log.error("error = {}", date);
//        log.info("info = {}", date);
//        log.debug("debug = {}", date);
//        log.warn("info = {}", date);
        loginService.login();
    }
}
