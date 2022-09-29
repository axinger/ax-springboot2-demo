package com.axing.demo.controller;

import com.axing.common.redis.service.RedisService;
import com.axing.common.response.result.Result;
import com.axing.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/add/{id}")
    public Result addValue(@PathVariable Integer id) {
        Person person = Person.builder().id(id).name("jim").age(21).build();
        redisService.set("demo13:person_"+id, person);
        return Result.ok();
    }

    @GetMapping("/get/{id}")
    public Result getValue(@PathVariable Integer id) {
        Person person = redisService.getCacheObject("demo13:person_" + id);
        return Result.ok(person);
    }

}
