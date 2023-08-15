package com.axing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分数据库（Database）：不同租户的数据放置在不同的数据中。不同数据源
 */
@SpringBootApplication
public class MultiTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiTenantApplication.class, args);
    }
}
