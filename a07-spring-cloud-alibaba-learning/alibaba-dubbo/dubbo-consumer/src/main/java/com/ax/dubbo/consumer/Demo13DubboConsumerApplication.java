package com.ax.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Demo13DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo13DubboConsumerApplication.class, args);
    }

}
