package com.axing.demo.db2.service;

import com.axing.demo.db2.entity.UserRole;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserRoleServiceTest {

    @Autowired
    private UserRoleService userRoleService;


    /**
     * 动态数据源: 方法中使用ds注解 ,test测试不出来
     */
    @Test
    @DS("db_ax_demo")
    void test1() {

        if (userRoleService != null) {
            final List<UserRole> list = userRoleService.list();
            System.out.println("list = " + list);
        } else {
            System.out.println("userRoleService = " + userRoleService);
        }

    }
}
