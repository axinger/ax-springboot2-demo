package com.axing.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = {"com.axing.demo.mapper","com.axing.demo.db2.mapper"})
public class A16MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(A16MybatisPlusApplication.class, args);
    }

}
