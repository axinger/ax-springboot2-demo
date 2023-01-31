package com.axing.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.axing.demo.mapper")
public class PostgresDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostgresDemoApplication.class, args);
    }
}
