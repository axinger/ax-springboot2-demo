package com.axing.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
//        MongoAutoConfiguration.class})

@SpringBootApplication
//@ComponentScan(basePackages = {"com.axing.common.response", "com.axing.common.util", "com.axing.demo"})
public class A11DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(A11DemoApplication.class, args);
    }

}
