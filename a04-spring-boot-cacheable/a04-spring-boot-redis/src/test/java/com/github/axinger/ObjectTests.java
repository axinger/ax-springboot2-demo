package com.github.axinger;


import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.axinger.model.User;
import com.github.axinger.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class ObjectTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper; // 注入全局 ObjectMapper

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void test1() {

        final User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();

        final User user = User.builder()
                .id(1)
                .name("jim")
                .age(21)
                .date(new Date())
                .books(List.of(book))
                .build();

        final String key = "test:user:1:User";
        redisTemplate.delete(key);
        this.redisTemplate.opsForValue().set(key, user, 1, TimeUnit.HOURS);

        // 设置键的字符串值并返回其旧值
//        User andSet = this.redisTemplateUser.opsForValue().getAndSet(key, user);
//
//        System.out.println("andSet = " + andSet);

        // 可以直接存, 不能直接取
        // final Map user1 = (Map) this.redisTemplateUser.opsForValue().get(key);
        // System.out.println("user1 = " + user1);

//        {
//
//            final User user2 = (User) this.redisTemplate.opsForValue().get(key);
//
//            Assertions.assertNotNull(user2);
//            System.out.println("user2 getClass= " + user2.getClass());
//            System.out.println("user2 = " + user2);
//
//        }

        {

            User user1 = redisUtil.get(key, User.class);
            System.out.println("user1 = " + user1);

        }
        {
            final Object user3 = this.redisTemplate.opsForValue().get(key);
            Assertions.assertNotNull(user3);
            System.out.println("user2 getClass= " + user3.getClass());
            System.out.println("user3 = " + user3);

            User user1 = JSON.parseObject(JSON.toJSONString(user3), User.class);
            System.out.println("user1 = " + user1);

            // 否则从 LinkedHashMap 或 JSON 字符串转
            try {
                String json = objectMapper.writeValueAsString(user3);
                User user2 = objectMapper.readValue(json, User.class);
                System.out.println("user2 = " + user2);
            } catch (Exception e) {
                throw new RuntimeException("Failed to deserialize to User", e);
            }
        }
    }
}
