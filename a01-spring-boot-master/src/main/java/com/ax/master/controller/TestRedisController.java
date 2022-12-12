package com.ax.master.controller;

import cn.hutool.core.util.IdUtil;
import com.ax.master.entity.Userinfo;
import com.axing.common.redis.service.RedisService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisController {


    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/doRedis.do")
    public void doRedis() {
        redisService.set("key_redis_nane", "jim");
    }


    @RequestMapping(value = "/getRedis.do")
    public Object getRedis() {

        return redisService.getCacheObject("key_redis_nane");
    }


    @GetMapping("/redis.do")
    public Object hello() {
        final Userinfo userinfo = Userinfo.builder()
                .id(1)
                .userName("jim")
                .userType(1)
                .password(IdUtil.simpleUUID().substring(0, 6))
                .build();
        redisService.set("userinfo", userinfo);

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
