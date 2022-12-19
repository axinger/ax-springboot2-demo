package com.axing.demo;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.axing.demo.service.RedisCacheTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName RedissonTests.java
 * @Description TODO
 * @createTime 2022年02月22日 19:23:00
 */
@SpringBootTest
@Slf4j
public class RedissonTests {

    @Autowired
    RedissonClient redissonClient;


    @Test
    void test1() {
        System.out.println("redissonClient = " + redissonClient);
    }

}
