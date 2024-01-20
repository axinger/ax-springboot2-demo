package com.axing.demo.controller;

import com.axing.demo.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class TestController {

    private int count;

    @Autowired
    private CountService countService;
    @Autowired
    private WebClient webClient;

    @GetMapping("/count")
    public Object count1() {
        count++;
        return List.of(count);
    }

    @GetMapping("/count2")
    public Object count2() {
        return List.of(countService.count());
    }

    // @Operation(summary = "MTR-获取物料信息")
    @GetMapping("/b/{materialCode:.*}")
    public Object getInfoByCode(@PathVariable("materialCode") String materialCode) {
        return List.of(materialCode);
    }

    //方式一，直接调用create()方法
    @GetMapping("/test1")
    public String test1() {
        Mono<String> mono = webClient
                .get() // GET 请求
                .uri("http://localhost:8080/a/person")  // 请求路径
                .retrieve() // 获取响应体
                .bodyToMono(String.class); //响应数据类型转换
        return "from test1 " + mono.block();
    }
}
