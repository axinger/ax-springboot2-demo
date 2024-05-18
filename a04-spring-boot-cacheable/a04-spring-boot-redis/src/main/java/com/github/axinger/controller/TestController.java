package com.github.axinger.controller;

import com.github.axinger.model.Order;
import com.github.axinger.model.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/sendOrder")
    public Object sendOrder(@RequestBody Order order) {
        redisTemplate.convertAndSend(RedisKeys.PLACE_ORDER_KEY, order);
        return order;
    }
}
