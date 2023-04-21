package com.axing.demo.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/get")
@Validated // get请求校验参数,不封装
public class GetController {

    @PostMapping("/login")
    public Object login(@RequestParam("username") @NotBlank String username,
                        @RequestParam("password") @NotBlank String password) {
        return List.of(username, password);
    }


}
