package com.github.axinger.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/get")
@Validated // get请求校验参数,不封装
public class GetController {

    @GetMapping("/login")
    public Object login(@RequestParam("username") @NotEmpty(message = "username不能为空") String username,
                        @RequestParam @NotBlank(message = "password不能为空") @Length(min = 2, message = "password长度不能小于2") String password) {
        return List.of(username, password);
    }

    @GetMapping({
            "/test1",
            "/test1/{id}"
    })
    @Validated
    public Object test1(@PathVariable(required = false, name = "id") String id) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", id);
        return map;
    }

    /// get参数list  [...](http://localhost:12021/get/test2?param=2&param=1)
    /// [...](http://localhost:12021/get/test2?param=1,2,3)",
    @GetMapping("/test2")
    public Object test3(@RequestParam("param") List<String> id) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", id);
        return map;
    }


}
