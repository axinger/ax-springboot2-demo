package com.axing.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AdminControllerApi", description = "管理员相关接口")
@RestController
@RequestMapping("/admin")
public class AdminController {
}
