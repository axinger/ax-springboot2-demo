package com.axing.demo.controller;

import com.axing.common.response.result.Result;
import com.axing.demo.vo.AdminVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Tag(name = "AdminControllerApi", description = "管理员相关接口")
@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping
    @Operation(summary = "管理信息",description = "管理信息")
    public Result<AdminVo> data() {
        AdminVo adminVo = new AdminVo();
        adminVo.setPortList(Arrays.asList("add", "delete"));
        return Result.ok(adminVo);
    }
}
