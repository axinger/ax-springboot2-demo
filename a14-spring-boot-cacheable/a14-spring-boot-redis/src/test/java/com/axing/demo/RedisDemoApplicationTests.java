package com.axing.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void contextLoads() {

        for (int i = 0; i < 100; i++) {
            int nextInt = new Random().nextInt(500);
            System.out.println("nextInt = " + nextInt);
        }
    }

}
