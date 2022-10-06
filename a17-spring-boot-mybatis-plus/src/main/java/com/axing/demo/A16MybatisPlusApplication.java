package com.axing.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.axing.demo.**.mapper")
@ComponentScan(basePackages = {"com.axing.demo", "com.axing.common.response"})
public class A16MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(A16MybatisPlusApplication.class, args);
    }

}
