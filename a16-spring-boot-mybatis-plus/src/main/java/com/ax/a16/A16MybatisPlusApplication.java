package com.ax.a16;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ax.a16.mapper")
public class A16MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(A16MybatisPlusApplication.class, args);
    }

}
