package com.ax.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "tags值", value = "value不显示")
@RestController
public class HoneController {

    @ApiOperation("参数接口")
    @GetMapping(value = "/login")
    public Object login(@ApiParam(value = "姓名") String name) {

        Map map = new HashMap<String, Object>(3);
        map.put("success", true);
        return map;
    }
}
