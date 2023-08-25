package com.axing.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.axing.common.redis.service.RedisService;
import com.axing.common.redis.util.RedisUtil;
import com.axing.common.response.result.Result;
import com.axing.demo.model.User;
import com.axing.demo.service.DemoUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@CacheConfig(cacheNames = "demo14:user")
public class UserController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private DemoUserService demoUserService;
    @Autowired
    private RedisTemplate<String, User> redisTemplate2;
    @Autowired
    private RedisTemplate<String, User> redisTemplateUser;

    @Operation(summary = "RedisTemplate获取数据")
    @GetMapping("/get/{id}")
    public Result getValue(@PathVariable Integer id) {

        String key = RedisUtil.getKey("demo13", "person", id);
        User person;
        if (redisService.hasKey(key)) {
            person = redisService.getCacheObject("demo13:person:" + id);
        } else {
            User.Book book = User.Book.builder()
                    .id(1)
                    .name("海底两万里")
                    .build();
            person = User.builder()
                    .id(id)
                    .name("jim")
                    .age(21)
                    .date(new Date())
                    .books(List.of(book))
                    .build();
        }
        return Result.ok(person);
    }

    @Operation(summary = "RedisTemplate添加数据")
    @GetMapping("/add/{id}")
    public Result addValue(@PathVariable Integer id) {
        User.Book book = User.Book.builder()
                .id(1)
                .name("海底两万里")
                .build();
        User person = User.builder()
                .id(id)
                .name("jim")
                .age(21)
                .date(new Date())
                .books(List.of(book))
                .build();
        redisService.set("demo13:user:" + id, person);
        return Result.ok();
    }

    @Operation(summary = "Cacheable获取数据", description = "redis没有数据,就从数据库中取")
    @GetMapping(value = "/get")
    public Object getUserFromCacheable(Integer id) {
        System.out.println("getUser...........");
        return demoUserService.findUser(id);
    }

    @Operation(summary = "Cacheable更新数据")
    @PostMapping(value = "/update")
    public Object update(Integer id) {
        User user = User.builder().id(id).name("jim" + new Random().nextInt(100)).age(10).build();
        return demoUserService.updateUser(user);
    }

    @Operation(summary = "Cacheable删除数据")
    @DeleteMapping(value = "/delete")
    public boolean deleteUser(Integer id) {
        System.out.println("deleteUser...........");
        demoUserService.deleteUser(id);
        return true;
    }

    @Operation(summary = "Cacheable更新数据")
    @GetMapping(value = "/last")
    public Result getLastName(Integer id) {
        System.out.println("getLastName...........");
        return Result.ok(demoUserService.getLastName(id));
    }

    @Cacheable(key = "#id")
    @GetMapping(value = "/key")
    public String getByKey(Long id) {
        return "我的jim" + id;
    }

    @GetMapping(value = "/user")
    public User user(Long id) {

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

        String key = "test:user";
        redisTemplate2.opsForValue().set(key, user);

        User user1 = redisTemplate2.opsForValue().get(key);
        System.out.println("user1 = " + user1.getLocalDateTime().toString());

        return user1;
    }

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
        return StrUtil.format("test:value:{}:User", id);
    }

    @GetMapping(value = "/opsForValue/set")
    public Object opsForValue() {
        redisTemplateUser.opsForValue().set(getUserKey(1), getUser(1), 120, TimeUnit.SECONDS);
        return true;
    }

    @GetMapping(value = "/opsForValue/setIfAbsent")
    public Object setIfAbsent() {
        // 当前key不存在，写入值, 并返回true; 当前key已经存在，不处理, 返回false;  Absent: 缺少的，
        Boolean ifAbsent = redisTemplateUser.opsForValue().setIfAbsent(getUserKey(1), getUser(1));
        System.out.println("ifAbsent = " + ifAbsent);
        return ifAbsent;
    }

    @GetMapping(value = "/opsForValue/setIfPresent")
    public Object setIfPresent() {
        // 当前key已经存在，写入值, 并返回true; 当前key不存在，不处理, 返回false;  ;Present: 存在的
        Boolean ifPresent = redisTemplateUser.opsForValue().setIfPresent(getUserKey(1), getUser(1));
        System.out.println("ifPresent = " + ifPresent);
        return ifPresent;
    }

    @GetMapping(value = "/opsForValue/getAndSet")
    public Object getAndSet() {
        // 获取原来key的value, 再将新的value写入
        User andSet = redisTemplateUser.opsForValue().getAndSet(getUserKey(1), getUser(1));
        System.out.println("andSet = " + andSet);
        return andSet;
    }

}
