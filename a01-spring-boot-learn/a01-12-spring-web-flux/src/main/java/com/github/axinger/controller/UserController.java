package com.github.axinger.controller;


import com.github.axinger.entity.User;
import com.github.axinger.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mvc")
public class UserController {
    /// flux 注解方法,和springmvc 类似
    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    public Mono<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @SneakyThrows
    @GetMapping("/sleep/{id}")
    public Object sleep(@PathVariable long id) {
        TimeUnit.SECONDS.sleep(id);
        return Map.of("time", id);
    }


    // 查询所有用户 Flux 多个返回值
    @GetMapping("/user")
    public Flux<Object> getAllUser() {
        return userService.getAllUser();
    }


    // 添加用户, 注意 参数类型 Mono<User>
    @PostMapping("/add")
    public Mono<Void> addUser(@RequestBody User user) {
        Mono<User> userMono = Mono.just(user);
        return userService.addUser(userMono);
    }

}
