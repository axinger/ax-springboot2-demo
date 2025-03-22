package com.github.axinger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(value = {"com.github.axinger.db1.mapper", "com.github.axinger.db2.mapper"})
public class A0202Application {

    public static void main(String[] args) {
        SpringApplication.run(A0202Application.class, args);
    }

}
