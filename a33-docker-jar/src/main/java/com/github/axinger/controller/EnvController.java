package com.github.axinger.controller;

import com.alibaba.fastjson2.JSONObject;
import com.axing.common.response.dto.Result;
import com.github.axinger.bean.ApplicationInfo;
import com.github.axinger.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    @Autowired
    private ApplicationInfo applicationInfo;

    @Autowired
    private Person person;

    @GetMapping("/")
    public Result<?> test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("person", person);
        map.put("applicationInfo", applicationInfo);

        return Result.success(JSONObject.from(map));
    }
}
