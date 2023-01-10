package com.axing.demo;

import com.axing.demo.config.UserProperties;
import com.axing.same.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvcApplicationTest {

    @Autowired
    UserService userService;
    @Autowired
    private UserProperties userProperties;

    // @Autowired
    // private UserService userService;
    //
    // @Test
    // void test_UserService(){
    //     System.out.println("userService = " + userService);
    // }

    @Test
    void test1() {
        System.out.println("userConfig.getList() = " + userProperties);
        String username = userProperties.getUser().username();
        System.out.println("username = " + username);
    }

    @Test
    void test_UserService2() {
        System.out.println("userService2 = " + userService);
    }


}
