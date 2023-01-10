package com.axing.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan(value = {"com.axing.demo.mapper", "com.axing.demo.db2.mapper","com.axing.demo.db3.mapper"})
// @MapperScan(value = {"com.axing.demo.mapper","com.axing.demo.*.mapper"})
// @MapperScan(value = {"com.axing.demo.*.mapper","com.axing.db.mapper"})
@MapperScan(value = {"com.axing.demo.*.mapper"})
// @MapperScans(value = {
//         @MapperScan(value = {"com.axing.demo.*.mapper"}),
//         @MapperScan(value = {"com.axing.db.mapper"})
// })
public class DBDemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(DBDemo3Application.class, args);
    }

}
