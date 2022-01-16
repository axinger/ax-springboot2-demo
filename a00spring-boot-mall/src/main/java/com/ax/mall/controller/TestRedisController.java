package com.ax.mall.controller;

import com.ax.mall.entity.Userinfo;
import com.ax.mall.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestRedisController {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/doRedis.do")
    public void doRedis() {
        redisService.set("key_redis_nane", "jim");
    }


    @RequestMapping(value = "/getRedis.do")
    public Object getRedis() {

        return redisService.get("key_redis_nane");
    }


    @GetMapping("/redis.do")
    public Object hello() {
        final Userinfo userinfo = Userinfo.builder()
                .id(1)
                .userName("jim")
                .userType(1)
                .build();
        redisTemplate.opsForValue().set("userinfo", userinfo);

        return "redis set 成功";
    }

    @GetMapping("/redisSession.do")
    public Object redisSession(HttpSession session) {
        final Userinfo userinfo = Userinfo.builder()
                .id(1)
                .userName("jim")
                .userType(1)
                .build();
        session.setAttribute("redisSession", userinfo);
        return "redis set 成功";
    }


}
