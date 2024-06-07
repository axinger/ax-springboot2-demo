package com.github.axinger.controller;

import com.alibaba.fastjson2.JSONObject;
import com.axing.common.response.result.Result;
import com.github.axinger.bean.ApplicationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
    @Autowired
    private ApplicationInfo applicationInfo;

    @GetMapping("/")
    public Result<?> test2() {
        return Result.ok(JSONObject.from(applicationInfo));
    }
}
