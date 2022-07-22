package com.ax.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
//        MongoAutoConfiguration.class})

@SpringBootApplication
@ComponentScan(basePackages = {"com.axing.common.response", "com.axing.common.util", "com.ax.demo"})
public class A11DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(A11DemoApplication.class, args);
    }

}
