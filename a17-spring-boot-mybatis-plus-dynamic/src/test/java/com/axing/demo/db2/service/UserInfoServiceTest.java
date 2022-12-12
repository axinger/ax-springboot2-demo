package com.axing.demo.db2.service;

import com.axing.demo.db2.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    UserInfoService userInfoService;


    @Test
    void test1() {
        final List<UserInfo> list = userInfoService.list();
        System.out.println("list = " + list);
    }
}
