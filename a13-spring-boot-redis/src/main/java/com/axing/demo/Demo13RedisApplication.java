package com.axing.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.axing.demo"})
public class Demo13RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo13RedisApplication.class, args);
    }
}
