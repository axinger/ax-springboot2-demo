package com.ax.mall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 打包 war 必须改继承 SpringBootServletInitializer
 * 同时用springboot 打包插件
 *
 * @author xing
 */
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class MallApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }
}

