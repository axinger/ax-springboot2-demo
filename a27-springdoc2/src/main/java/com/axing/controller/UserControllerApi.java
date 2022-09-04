package com.axing.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserControllerApi", description = "用户的增删改查")
@RestController
@RequestMapping("/user")
public class UserControllerApi {

}