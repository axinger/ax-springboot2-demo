package com.github.axinger.controller;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController {

    @NotEmpty(message = "name不能为空")
//    private String name;
    public String name;

    @GetMapping("/list")
    public void list() {

    }
}
