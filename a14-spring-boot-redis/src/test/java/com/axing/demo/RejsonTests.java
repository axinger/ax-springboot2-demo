package com.axing.demo;

import com.alibaba.fastjson2.JSONObject;
import com.axing.common.redis.service.RedisService;
import com.axing.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
public class RejsonTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;


    @Test
    void test(){

//        Object peron = redisService.getCacheObject("peron");
//        System.out.println("peron = " + peron);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","jim");

        redisTemplate.opsForValue().set("dog", jsonObject);

    }

    @Test
    void test1(){

        User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();
        User  user = User.builder()
                .id(1)
                .name("jim")
                .age(21)
                .date(new Date())
                .books(List.of(book))
                .build();


        redisTemplate.opsForValue().set("user", user);

    }

    @Test
    void test_get(){
        Object o = redisTemplate.opsForValue().get("user:name");
        System.out.println("o = " + o);


    }
}
