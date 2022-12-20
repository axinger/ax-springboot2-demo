package com.axing.demo;

import cn.hutool.core.util.StrUtil;
import com.axing.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class OpsForValueTests {
    @Autowired
    private RedisTemplate<String, User> redisTemplateUser;

    private User getUser(Integer id) {
        final User.Book book = User.Book.builder()
                .id(id)
                .name("海底两万里")
                .build();

        final User user = User.builder()
                .id(id)
                .name("jim")
                .age(21)
                .date(new Date())
                .localDateTime(LocalDateTime.now())
                .books(List.of(book))
                .build();

        return user;
    }

    private String getUserKey(Integer id) {
        return StrUtil.format("test::value::{}::User", id);
    }

    @Test
    void opsForValue() {
        redisTemplateUser.opsForValue().set(getUserKey(1), getUser(1), 120, TimeUnit.SECONDS);
    }

    @Test
    void opsForValue_get() {
        User user = redisTemplateUser.opsForValue().get(getUserKey(1));
        System.out.println("user = " + user);
    }

    @Test
    void setIfAbsent() {
        // 当前key不存在，写入值, 并返回true; 当前key已经存在，不处理, 返回false;  Absent: 缺少的，
        Boolean ifAbsent = redisTemplateUser.opsForValue().setIfAbsent(getUserKey(1), getUser(1));
        System.out.println("ifAbsent = " + ifAbsent);
    }

    @Test
    void setIfPresent() {
        // 当前key已经存在，写入值, 并返回true; 当前key不存在，不处理, 返回false;  ;Present: 存在的
        Boolean ifPresent = redisTemplateUser.opsForValue().setIfPresent(getUserKey(1), getUser(1));
        System.out.println("ifPresent = " + ifPresent);
    }

    @Test
    void getAndSet() {
        // 获取原来key的value, 再将新的value写入
        User andSet = redisTemplateUser.opsForValue().getAndSet(getUserKey(1), getUser(1));
        log.info("andSet = {}", andSet);
    }
}
