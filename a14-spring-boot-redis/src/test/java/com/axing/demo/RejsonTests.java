package com.axing.demo;

import com.alibaba.fastjson2.JSONObject;
import com.axing.common.redis.service.RedisService;
import com.axing.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
@CacheConfig(cacheNames = "demo13::user")
public class RejsonTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;


    @Test
    void test() {

//        Object peron = redisService.getCacheObject("peron");
//        System.out.println("peron = " + peron);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jim");

        redisTemplate.opsForValue().set("demo::dog1", jsonObject);
        redisTemplate.opsForValue().set("demo:dog2", jsonObject);
    }

    @Autowired
    private RedisTemplate<String, User> redisTemplate2;

    @Test
    void test1() {

        User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();

        User user = User.builder()
                .id(1)
                .name("jim")
                .age(21)
                .date(new Date())
                .localDateTime(LocalDateTime.now())
                .books(List.of(book))
                .build();

        String key = "test::user";
        redisTemplate2.opsForValue().set(key, user);

        User user1 = redisTemplate2.opsForValue().get(key);
        System.out.println("user1 = " + user1.getLocalDateTime().toString());



    }

    @Test
    void test_get() {
        Object o = redisTemplate.opsForValue().get("user:name");
        System.out.println("o = " + o);


    }


    @Test
    void test_Cacheable() {
        System.out.println("getByKey(1L) = " + getByKey(1L));
    }


    @Cacheable(key = "#id")
    public String getByKey(Long id) {
        return "我的jim" + id;
    }
}
