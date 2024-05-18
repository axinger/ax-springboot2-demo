package com.github.axinger;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(excludeFilters = {
        // 去除装配
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        com.github.axinger.b.UserService.class
                })
})
@SpringBootApplication
public class A01MVCApplication {
    public static void main(String[] args) {
        SpringApplication.run(A01MVCApplication.class, args);
    }
}
