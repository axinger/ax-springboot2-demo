package com.ax.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void getAll() {

        List<Map<String, Object>> list = userService.getAll();
        System.out.println("list = " + list);
    }

    @Test
    void upDateAge() {
        userService.upDateAge("abc1");
    }

    @Test
    void upDateSex() {
        userService.upDateSex(3);
    }

    @Test
    void upDateAgeAndSex() {
        userService.upDateAgeAndSex("aaa1234", 1234);
    }
}
