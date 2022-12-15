package com.axing.demo.controller;

import com.axing.demo.config.TokenHolder;
import com.axing.demo.config.UmsAdminApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UmsAdminController {

    @Autowired
    private UmsAdminApi umsAdminApi;

    @Autowired
    private TokenHolder tokenHolder;

    @Tag(name = "调用远程登录接口获取token")
    @PostMapping(value = "/admin/login")
    public Map login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        tokenHolder.putToken("token:"+username);
        return map;
    }


    @Tag(name = "测试登录")
    @PostMapping(value = "/test/login")
    public Object testLogin(@RequestParam String username, @RequestParam String password) {
        Map map = umsAdminApi.login(username, password);
        return map;
    }

    @Tag(name = "测试登录")
    @PostMapping(value = "/token")
    public Object token(@RequestHeader("Authorization") String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
