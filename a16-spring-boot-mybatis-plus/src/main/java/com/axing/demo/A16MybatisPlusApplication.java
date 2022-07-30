package com.axing.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.axing.demo", "com.axing.common.response"})
public class A16MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(A16MybatisPlusApplication.class, args);
    }

}
