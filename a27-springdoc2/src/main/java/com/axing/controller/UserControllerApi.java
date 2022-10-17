package com.axing.controller;

import com.axing.common.response.result.Result;
import com.axing.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserControllerApi", description = "用户的增删改查")
@RestController
@RequestMapping("/user")
public class UserControllerApi {


    @GetMapping
    @Operation(summary = "用户信息",description = "用户信息")
    public Result<UserVo> data() {
        UserVo userVo = new UserVo();
        userVo.setName("jim");
        return Result.ok(userVo);
    }
}