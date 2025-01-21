package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNettyApplication.class, args);
    }

}
