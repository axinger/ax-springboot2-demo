package com.ax.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author xing
 */
@SpringBootApplication
@EnableAsync
public class MvcWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MvcWebApplication.class, args);

        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("默认ioc name = " + name);
        }
        hostAndContextPath(run);
    }


    static void hostAndContextPath(ConfigurableApplicationContext context) {

        try {

            ConfigurableEnvironment environment = context.getEnvironment();
            Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
            Map<String, Object> systemProperties = environment.getSystemProperties();

            System.out.println("environment = " + environment);
            System.out.println("systemProperties = " + systemProperties);
            System.out.println("systemEnvironment = " + systemEnvironment);


            String host = InetAddress.getLocalHost().getHostAddress();
            TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) context.getBean("tomcatServletWebServerFactory");
            int port = tomcatServletWebServerFactory.getPort();
            String contextPath = tomcatServletWebServerFactory.getContextPath();

            String dateString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm.SSSS").format(LocalDateTime.now());

            System.out.println("\n");
            System.out.println("-------------->" + "监听tomcat启动 hostAndContextPath>> " + dateString);

            System.out.println("地址是: http://" + host + ":" + port + contextPath + "/");
            System.out.println("地址是: http://" + "localhost:" + port + contextPath + "/");

            System.out.println("swagger地址是: http://" + "localhost:" + port + contextPath + "/swagger-ui/index.html");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
