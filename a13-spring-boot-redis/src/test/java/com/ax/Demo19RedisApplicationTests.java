package com.ax;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class Demo19RedisApplicationTests {

    @Test
    void contextLoads() {

        for (int i = 0; i < 100; i++) {
            int nextInt = new Random().nextInt(500);
            System.out.println("nextInt = " + nextInt);
        }
    }

}
