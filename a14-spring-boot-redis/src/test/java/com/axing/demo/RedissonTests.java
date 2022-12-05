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

    private static final String SERIAL_NUM = "redis:serialNumber:";

    @Autowired
    private RedisCacheTemplate redisCacheTemplate;

    /**
     * 自增流水号
     */
    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            testNum();
        }
    }

    void testNum() {
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.plusDays(1);
        final String currentDate = LocalDateTimeUtil.format(dateTime, "yyyy-MM-dd");
        String key = SERIAL_NUM + currentDate;
        // 过期时间 60*60*24
        long incr = redisCacheTemplate.incr(key, 1, 86400);
        // 左对齐
        String value = StrUtil.padPre(String.valueOf(incr), 6, "0");
        // 然后把 时间戳和优化后的 ID 拼接
        String code = StrUtil.format("{}-{}", currentDate, value);
        System.out.println("code = " + code);
    }
}
