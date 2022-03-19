package com.ax.mall.controller;

import cn.hutool.core.util.IdUtil;
import com.ax.mall.entity.Userinfo;
import com.ax.mall.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

@RestController
public class TestRedisController {


    @Autowired
    RedisService redisService;
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

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
                .password(IdUtil.simpleUUID().substring(0, 6))
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
