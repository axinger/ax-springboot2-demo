package com.ax.service;

import com.ax.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    /// 模拟数据
    private final Map<Integer, User> userMap = new HashMap<>();

    // 无参构造,初始化数据
    public UserServiceImpl() {
        for (int i = 1; i <= 5; i++) {
            userMap.put(i, new User("jim" + i, (i % 2 == 0 ? "男" : "女"), 20 + i));
        }
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(userMap.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        /// userMap.values 取集合
        return Flux.fromIterable(userMap.values());
    }

    @Override
    public Mono<Void> addUser(Mono<User> userMono) {

        return userMono.doOnNext(user -> {
            Integer id = userMap.size() + 1;
            userMap.put(id, user);
        }).thenEmpty(Mono.empty());

    }
}
