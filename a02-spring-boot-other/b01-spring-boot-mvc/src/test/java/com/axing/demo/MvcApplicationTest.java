package com.axing.demo;

import com.axing.demo.config.UserProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvcApplicationTest {

    @Autowired
    private UserProperties userProperties;

    @Test
    void test1() {
        System.out.println("userConfig.getList() = " + userProperties);
        String username = userProperties.getUser().username();
        System.out.println("username = " + username);
    }
}
