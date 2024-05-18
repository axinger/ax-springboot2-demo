package com.github.axinger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @MapperScan(value = {"com.github.axinger.mapper", "com.github.axinger.db2.mapper","com.github.axinger.db3.mapper"})
// @MapperScan(value = {"com.github.axinger.mapper","com.github.axinger.*.mapper"})
// @MapperScan(value = {"com.github.axinger.*.mapper","com.axing.db.mapper"})
@MapperScan(value = {"com.github.axinger.*.mapper"})
// @MapperScans(value = {
//         @MapperScan(value = {"com.github.axinger.*.mapper"}),
//         @MapperScan(value = {"com.axing.db.mapper"})
// })
public class DBDemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(DBDemo3Application.class, args);
    }

}
