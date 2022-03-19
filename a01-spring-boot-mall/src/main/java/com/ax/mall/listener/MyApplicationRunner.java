package com.ax.mall.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ApplicationRunner和CommandLineRunner，来实现，他们都是在SpringApplication 执行之后开始执行的。
 * CommandLineRunner接口可以用来接收字符串数组的命令行参数，
 * ApplicationRunner 是使用ApplicationArguments 用来接收参数的，貌似后者更牛逼一些。
 */

@Component
@Order(1)
/**如果多个自定义ApplicationRunner，用来标明执行顺序*/
/**
 * @author axing
 */
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments applicationArguments) {

        String dateString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm.SSSS").format(LocalDateTime.now());

        System.out.println("\n");
        System.out.println("-------------->" + "监听Springboot启动>> " + dateString);
        System.out.println("获取到的参数 getOptionNames ： " + applicationArguments.getOptionNames());
        System.out.println("获取到的参数 getNonOptionArgs： " + applicationArguments.getNonOptionArgs());
        System.out.println("获取到的参数 getSourceArgs： " + applicationArguments.getSourceArgs());

        try {

            String host = InetAddress.getLocalHost().getHostAddress();
            AbstractServletWebServerFactory serverFactory = applicationContext.getBean(AbstractServletWebServerFactory.class);
            int port = serverFactory.getPort();
            String contextPath = serverFactory.getContextPath();

            log.debug("地址是: http://" + host + ":" + port + contextPath + "/");
            log.info("info地址是: http://" + "localhost:" + port + contextPath + "/");
            log.debug("debug地址是: http://" + "localhost:" + port + contextPath + "/");
            log.info("swagger地址是: http://" + "localhost:" + port + contextPath + "/swagger-ui/index.html");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
