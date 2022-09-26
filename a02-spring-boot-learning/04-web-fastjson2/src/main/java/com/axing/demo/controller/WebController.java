package com.axing.demo.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {
    /**
     * fromData
     *
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/web1")
    public Object fromData(@RequestParam("name") String name, @RequestParam Integer age) {
        Map map = new HashMap();
        map.put("localDateTime", LocalDateTime.now());
        map.put("name", name);
        map.put("age", age);
        return map;

    }

    /**
     * @RequestBody只能有一个
     *
     * @param json
     * @return
     */
    @PostMapping(value = "/web2")
    public Object json(@RequestBody JSONObject json) {
        Map map = new HashMap();
        map.put("localDateTime", LocalDateTime.now());
        map.put("name", json.getString("name"));
        map.put("age", json.getIntValue("age"));
        return map;

    }
}
