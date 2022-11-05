package com.axing.demo;

import com.alibaba.fastjson2.JSON;
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
    private RedisTemplate<String, User> redisTemplate2;

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
                .localDateTime(LocalDateTime.now())
                .books(List.of(book))
                .build();

        final String key = "test::user";
        this.redisTemplate2.opsForValue().set(key, user);

        final User user1 = this.redisTemplate2.opsForValue().get(key);
        System.out.println("user1 = " + user1);


        final Object user2 = this.redisTemplate.opsForValue().get(key);

        System.out.println("user2 = " + user2);
    }

    @Test
    void test_json_1() {

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

        final String key = "test::json::user";

        final Map map = JSON.parseObject(JSON.toJSONString(user), Map.class);

//        final Map map = new HashMap<>();
//        map.put("name",123);

                this.redisTemplate.opsForValue().set(key, map);

        final Object user1 = this.redisTemplate.opsForValue().get(key);

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
        System.out.println("getByKey(1L) = " +getByKey(1L));
    }


    @Cacheable(key = "#id")
    public String getByKey(final Long id) {
        return "我的jim" + id;
    }
}
