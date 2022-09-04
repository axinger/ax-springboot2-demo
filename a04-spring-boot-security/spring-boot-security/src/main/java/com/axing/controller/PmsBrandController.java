package com.axing.controller;

import com.axing.common.response.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 品牌管理Controller
 * Created by macro on 2019/4/19.
 */
@Tag(name = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {


    @Operation(summary = "获取所有品牌列表", description = "需要登录后访问")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public Result getBrandList() {
        return Result.ok("获取所有品牌列表");
    }


}
