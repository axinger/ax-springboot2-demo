package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class A07NacosProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(A07NacosProviderApplication.class, args);
    }
}

