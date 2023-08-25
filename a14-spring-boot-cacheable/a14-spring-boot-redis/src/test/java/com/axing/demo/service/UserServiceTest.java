package com.axing.demo.service;

import com.axing.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void findUser() {
        User user = userService.findUser(1);
        System.out.println("user = " + user);
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getLastName() {
    }
}
