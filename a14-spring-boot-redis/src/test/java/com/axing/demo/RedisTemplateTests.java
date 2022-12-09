package com.axing.demo;

import com.alibaba.fastjson2.JSONObject;
import com.axing.common.redis.service.RedisService;
import com.axing.common.redis.util.RedisUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/weixin_40461281/article/details/82011670
 */
@SpringBootTest
@Slf4j
@CacheConfig(cacheNames = "demo13::user")
public class RedisTemplateTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate<String, User> redisTemplateList;
    @Autowired
    private RedisTemplate<String, User> redisTemplateUser;
    @Autowired
    private RedisTemplate<String, Map> redisTemplateMap;
    @Autowired
    private ObjectMapper objectMapper;

    User user(Integer id) {
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

    @Test
    void opsForHash() {
        final String key = "test::hash::1::User";

        Map valuesMap = new HashMap<>();
        valuesMap.put("map", new HashMap<>() {{
            put("name", "jim");
        }});

        // redisTemplate.opsForHash().put(key, "name", valuesMap);
        redisTemplate.opsForHash().putAll(key, valuesMap);

    }

    @Test
    void opsForList_leftPush() {
        // 将所有指定的值插入存储在键的列表的头部
        final String key = "test::list::1::User";

        Long aLong = redisTemplateList.opsForList().leftPush(key, user(1));
        System.out.println("aLong = " + aLong);

        Long aLon2 = redisTemplateList.opsForList().leftPush(key, user(2));
        System.out.println("aLong = " + aLon2);
    }

    @Test
    void opsForList_range() {
        // 不会越界
        final String key = "test::list::1::User";
        List<User> range = redisTemplateList.opsForList().range(key, 1, 3);
        System.out.println("range = " + range);
    }

    @Test
    void opsForList_index() {
        // 弹出最左边的元素，弹出之后该值在列表中将不复存在
        final String key = "test::list::1::User";
        User index = redisTemplateList.opsForList().index(key, 0);
        System.out.println("index = " + index);

        User index1 = redisTemplateList.opsForList().index(key, 1);
        System.out.println("index = " + index1);
    }

    @Test
    void opsForList_pop() {
        // 弹出最左边的元素，弹出之后该值在列表中将不复存在
        final String key = "test::list::1::User";
        User user = redisTemplateList.opsForList().rightPop(key);
        System.out.println("user = " + user);

        User user2 = redisTemplateList.opsForList().leftPop(key);
        System.out.println("user2 = " + user2);
    }

    @Test
    void test() {

//        Object peron = redisService.getCacheObject("peron");
//        System.out.println("peron = " + peron);

        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jim");

        this.redisTemplate.opsForValue().set("demo::dog1", jsonObject);
        this.redisTemplate.opsForValue().set("demo:dog2", jsonObject);
    }

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
        // this.redisTemplateUser.opsForValue().set(key, user);

        // 设置键的字符串值并返回其旧值
        User andSet = this.redisTemplateUser.opsForValue().getAndSet(key, user);

        System.out.println("andSet = " + andSet);

        // 可以直接存, 不能直接取
        // final Map user1 = (Map) this.redisTemplateUser.opsForValue().get(key);
        // System.out.println("user1 = " + user1);


        final User user2 = this.redisTemplateUser.opsForValue().get(key);

        System.out.println("user2 = " + user2);


    }

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

        // final String key = "test::user::1::Map";


        final String key = RedisUtil.getKey("test", "user", 1, "Map");
        System.out.println("key = " + key);

        Map map = objectMapper.readValue(objectMapper.writeValueAsString(user), Map.class);


        this.redisTemplateMap.opsForValue().set(key, map);

        final Map userMap = this.redisTemplateMap.opsForValue().get(key);

        System.out.println("userMap = " + userMap);


        User user1 = objectMapper.readValue(objectMapper.writeValueAsString(userMap), User.class);
        System.out.println("user1 = " + user1);

    }

    @Test
    void test_map_to_user() {

        final String key = "test::user::1::Map";
        User user = redisTemplateUser.opsForValue().get(key);
        System.out.println("user = " + user);
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


    @Test
    void test_expire() {

        final String key = "test::expire::1::Map";
        this.redisTemplate.opsForValue().set(key, "1");
        this.redisTemplate.expire(key, 5, TimeUnit.SECONDS);


    }

}
