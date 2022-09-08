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

