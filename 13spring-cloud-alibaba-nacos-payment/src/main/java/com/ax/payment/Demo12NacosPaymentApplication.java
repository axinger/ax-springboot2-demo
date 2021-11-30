package com.ax.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xing
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Demo12NacosPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo12NacosPaymentApplication.class, args);
    }

}
