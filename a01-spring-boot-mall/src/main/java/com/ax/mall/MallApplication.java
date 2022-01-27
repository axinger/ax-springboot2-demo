package com.ax.mall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 打包 war 必须改继承 SpringBootServletInitializer
 * 同时用springboot 打包插件
 * @author xing
 */
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class MallApplication extends SpringBootServletInitializer {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {

        applicationContext = SpringApplication.run(MallApplication.class, args);
        hostAndContextPath(applicationContext);

    }

    static void hostAndContextPath(ApplicationContext applicationContext) {

        try {

            String host = InetAddress.getLocalHost().getHostAddress();
            TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) applicationContext.getBean("tomcatServletWebServerFactory");
            int port = tomcatServletWebServerFactory.getPort();
            String contextPath = tomcatServletWebServerFactory.getContextPath();

            log.debug("地址是: http://" + host + ":" + port + contextPath + "/");
            log.info("info地址是: http://" + "localhost:" + port + contextPath + "/");
            log.debug("debug地址是: http://" + "localhost:" + port + contextPath + "/");
            log.info("swagger地址是: http://" + "localhost:" + port + contextPath + "/swagger-ui/index.html");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        applicationContext = builder.context();
        hostAndContextPath(applicationContext);
        return builder.sources(MallApplication.class);
    }

}

