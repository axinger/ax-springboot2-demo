package com.ax;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName RedissonTests.java
 * @Description TODO
 * @createTime 2022年02月22日 19:23:00
 */
@SpringBootTest
public class RedissonTests {

    @Autowired
    RedissonClient redissonClient;

    @Test
    void test1() {
        System.out.println("redissonClient = " + redissonClient);
    }
}
