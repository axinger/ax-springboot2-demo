package com.axing.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 打包 war 必须改继承 SpringBootServletInitializer
 * 同时用springboot 打包插件
 *
 * @author xing
 */
//@SpringBootApplication(scanBasePackages = {"com.ax", "com.axing"})
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class MasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MasterApplication.class, args);
    }
}

