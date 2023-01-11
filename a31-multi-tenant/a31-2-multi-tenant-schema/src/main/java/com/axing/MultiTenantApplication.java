package com.axing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 2、“分结构”多租户
 * 不同的租户数据放置在相同数据库实例的不同结构（Schema）中。
 */
@SpringBootApplication
public class MultiTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiTenantApplication.class, args);
    }
}
