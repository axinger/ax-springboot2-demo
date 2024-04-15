package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.github.axinger.mapper"})
public class A20QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(A20QuartzApplication.class, args);
    }

}
