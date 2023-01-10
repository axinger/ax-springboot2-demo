package com.axing.demo;


import com.axing.demo.b.UserService;
import com.axing.demo.model.Person;
import com.axing.demo.model.UserDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableConfigurationProperties(value = {UserDTO.class, Person.class})
@SpringBootApplication
@ComponentScan(excludeFilters = {
        // 去除装配
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        UserService.class
                })
})
public class MvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }
}
