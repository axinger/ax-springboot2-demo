package com.axing.demo.controller;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Service
@Validated
public class ValidationController {

    public String test(@NotBlank(message = "name不能为空字符串") String name) {

        return "1";
    }


}
