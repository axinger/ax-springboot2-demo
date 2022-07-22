package com.ax.a19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.axing"})
public class A19Application {

    public static void main(String[] args) {
        SpringApplication.run(A19Application.class, args);
    }

}
