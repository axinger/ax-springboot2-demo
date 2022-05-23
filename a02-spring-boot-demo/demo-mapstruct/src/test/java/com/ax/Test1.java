package com.ax;

import com.ax.model.Role;
import com.ax.model.User;
import com.ax.model.UserRoleDto;
import com.ax.model.UserRoleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test1 {


    Role role = null;
    User user = null;

    @BeforeEach
    public void before() {
        role = new Role(2L, "administrator", "超级管理员");
        user = new User(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role);
    }


    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
    public void test() {
        UserRoleDto userRoleDto = userRoleMapper.useParameter(user, "myUserRole");
        System.out.println(userRoleDto);
    }


    @Test
    public void test2() {

        UserRoleDto userRoleDto = userRoleMapper.toUserRoleDto(user);
        System.out.println("userRoleDto = " + userRoleDto);
    }

    @Test
    public void test3() {
        UserRoleDto userRoleDto = userRoleMapper.defaultConvert();
        System.out.println(userRoleDto);
    }
}
