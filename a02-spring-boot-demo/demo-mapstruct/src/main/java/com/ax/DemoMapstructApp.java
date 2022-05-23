package com.ax;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName DemoMapstructApp.java
 * @description TODO
 * @createTime 2022年05月21日 01:29:00
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.ax.model"})//扫描包
@SpringBootApplication
public class DemoMapstructApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoMapstructApp.class, args);
    }
}
