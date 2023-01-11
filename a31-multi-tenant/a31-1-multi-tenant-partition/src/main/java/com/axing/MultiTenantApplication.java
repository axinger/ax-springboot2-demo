package com.axing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分区（Partitioned）数据：不同租户的数据都在一张表里，通过一个值（tenantId）来区分不同的租户。
 */
@SpringBootApplication
public class MultiTenantApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiTenantApplication.class, args);
    }
}
