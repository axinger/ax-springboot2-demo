package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class A02Dynamic2Application {

    public static void main(String[] args) {
        SpringApplication.run(A02Dynamic2Application.class, args);
    }

}
