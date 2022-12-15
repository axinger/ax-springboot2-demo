package com.axing.demo.config;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

/**
 * @auther macrozheng
 * @description 定义Http接口，用于调用远程的UmsAdmin服务
 * @date 2022/1/19
 * @github https://github.com/macrozheng
 */
@HttpExchange
public interface UmsAdminApi {

    @PostExchange("admin/login")
    Map<String, Object> login(@RequestParam("username") String username, @RequestParam("password") String password);
}
