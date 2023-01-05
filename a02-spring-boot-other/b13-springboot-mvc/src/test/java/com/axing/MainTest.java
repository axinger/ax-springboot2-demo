package com.axing;


import cn.hutool.core.util.RandomUtil;
import com.axing.service.OauthContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class MainTest {

    @SneakyThrows
    @Test
    void test1() {
        // 1. 获取父线程的loginVal
        String loginVal = OauthContext.get();
        log.info("父线程1的值：{}", OauthContext.get());
        CompletableFuture.runAsync(() -> {
            // 子线程内部,第一次拿到的值为null, 保证了数据的干净
            log.info("子线程1,初始值：{}", OauthContext.get());
            // 2. 设置子线程的值，复用
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 子线程1修改值
                // loginVal 取,每次都是原始值,子线程set不会影响此值
                OauthContext.set(loginVal + RandomUtil.randomString(5));
                log.info("子线程1的值：{},{}", loginVal, OauthContext.get());
            }
        });

        CompletableFuture.runAsync(() -> {
            // 子线程内部,第一次拿到的值为null, 保证了数据的干净
            // 同时线程1修改值,不会影响线程2的值
            log.info("子线程2,初始值：{}", OauthContext.get());
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("子线程2的值：{},{}", loginVal, OauthContext.get());
            }
        });

        log.info("父线程2的值：{}", OauthContext.get());
        TimeUnit.MINUTES.sleep(5);
    }


}
