package com.github.axinger.service;

import com.github.axinger.domain.SysUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class SysUserServiceTest {


    @Autowired
    private SysUserService sysUserService;


    @Test
    void getSysUserById() {
        List<SysUserEntity> list = sysUserService.list();
        System.out.println("list = " + list);

    }
}
