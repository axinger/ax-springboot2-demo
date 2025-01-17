package com.github.axinger.service;

import com.github.axinger.domain.SysUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@SpringBootTest
public class SysUserServiceTest {


    @Autowired
    private SysUserService sysUserService;


    @Test
    void getSysUserById() {
        List<SysUserEntity> list = sysUserService.list();
        System.out.println("list = " + list);

    }

    @Test
    void test3() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 1000; i++) {
                sysUserService.lambdaUpdate()
                        .eq(SysUserEntity::getId, 1)
                        .set(SysUserEntity::getAge, i)
                        .update();
            }
        });

        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 1000; i++) {
                sysUserService.lambdaUpdate()
                        .eq(SysUserEntity::getId, 1)
                        .set(SysUserEntity::getAge, i)
                        .update();
            }
        });
        voidCompletableFuture.join();
    }
}
