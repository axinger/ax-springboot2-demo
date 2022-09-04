package com.axing.controller;

import com.axing.common.response.result.Result;
import com.axing.dto.LoginDto;
import com.axing.service.UmsAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@RestController
@Tag(name = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Operation(summary = "登录以后返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody LoginDto loginDto) {
        String token = adminService.login(loginDto.getUsername(), loginDto.getPassword());
        if (token == null) {
            return Result.fail("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.ok(tokenMap);
    }
}
