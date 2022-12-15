package com.axing.demo.config;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @auther macrozheng
 * @description 定义Http接口，用于调用远程的PmsBrand服务
 * @date 2022/1/19
 * @github https://github.com/macrozheng
 */
@HttpExchange
public interface PmsBrandApi {
    @GetExchange("brand/list")
    Object list(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

}
