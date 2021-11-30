package com.ax.handler;

import com.ax.entity.User;
import com.ax.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }


    public Mono<ServerResponse> getUserById(ServerRequest request) {

        // 获取参数
        Integer id = Integer.valueOf(request.pathVariable("id"));

        System.out.println("id = " + id);

        /// 空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        Mono<User> userMono = userService.getUserById(id);
        System.out.println("userMono = " + userMono);
        return userMono.flatMap(e -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
//                .body(fromObject(e))
                .body(userMono, User.class) /// user 需要无参构造
                .switchIfEmpty(notFound));
    }


    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        Flux<User> allUser = userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(allUser, User.class);
    }


    public Mono<ServerResponse> addUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(userService.addUser(userMono));
    }


}
