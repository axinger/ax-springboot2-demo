package com.axing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        User user = new User("jim", "123");
//        User user2 = new User();
        String name = user.name();
        System.out.println("name = " + name);

        User.range range = new User.range(1, 2);

        System.out.println(user.all());
        return user;
    }
}

/**
 * Java 16 新特性：record类
 * record申明的类，具备这些特点：
 * 因为record关键词申明类主要是为了简化一些类的申明，所以它本质就是一类特殊的class，或者说是某一个模版的class。
 * 它是一个final类
 * 自动实现equals、hashCode、toString函数
 * 成员变量均为public属性
 *
 * @param name
 * @param password
 */
record User(String name, String password) {

    public record range(int start, int end) {

    }

    String all() {
        return name + password;
    }

}