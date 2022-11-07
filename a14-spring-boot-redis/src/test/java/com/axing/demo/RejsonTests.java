package com.axing.demo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.axing.common.redis.service.RedisService;
import com.axing.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
import java.util.Map;

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

        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jim");

        this.redisTemplate.opsForValue().set("demo::dog1", jsonObject);
        this.redisTemplate.opsForValue().set("demo:dog2", jsonObject);
    }

    @Autowired
    private RedisTemplate<String, User> redisTemplateUser;

    @Test
    void test1_redisTemplateUser() {

        final User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();

        final User user = User.builder()
                .id(1)
                .name("jim")
                .age(21)
                .date(new Date())
                .localDateTime(LocalDateTime.now())
                .books(List.of(book))
                .build();

        final String key = "test::user::1::User";
        this.redisTemplateUser.opsForValue().set(key, user);

        // 可以直接存, 不能直接取
        final Map user1 = (Map) this.redisTemplateUser.opsForValue().get(key);
        System.out.println("user1 = " + user1);


//        final Object user2 = this.redisTemplate.opsForValue().get(key);
//
//        System.out.println("user2 = " + user2);
    }

    @Autowired
    private RedisTemplate<String,Map> redisTemplateMap;

    @Autowired
    private ObjectMapper objectMapper;
    @SneakyThrows
    @Test
    void test_map() {

        final User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();

        final User user = User.builder()
                .id(1)
                .name("jim")
                .age(21)
                .date(new Date())
                .localDateTime(LocalDateTime.now())
                .books(List.of(book))
                .build();

        final String key = "test::user::1::Map";


        Map map = objectMapper.readValue(objectMapper.writeValueAsString(user), Map.class);


        this.redisTemplateMap.opsForValue().set(key, map);

        final Map userMap = this.redisTemplateMap.opsForValue().get(key);

        System.out.println("userMap = " + userMap);


        User user1 = objectMapper.readValue(objectMapper.writeValueAsString(userMap), User.class);
        System.out.println("user1 = " + user1);

    }

    @Test
    void test_json() {
        final Object o = this.redisTemplate.opsForValue().get("test::json::user.id");
        System.out.println("o = " + o);


    }

    @Test
    void test_get() {
        final Object o = this.redisTemplate.opsForValue().get("user:name");
        System.out.println("o = " + o);


    }


    @Test
    void test_Cacheable() {
        System.out.println("getByKey(1L) = " + getByKey(1L));
    }


    @Cacheable(key = "#id")
    public String getByKey(final Long id) {
        return "我的jim" + id;
    }
}
