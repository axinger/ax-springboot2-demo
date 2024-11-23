package com.github.axinger.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ValidationController {

    public String test(@NotBlank(message = "name不能为空字符串") String name) {

        return "1";
    }


}
