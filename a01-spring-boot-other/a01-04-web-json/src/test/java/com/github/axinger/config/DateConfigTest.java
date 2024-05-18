package com.github.axinger.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class DateConfigTest {

    @Test
    void test_date() {
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());

    }
}
