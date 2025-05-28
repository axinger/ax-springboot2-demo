package com.github.axinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class A21GrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(A21GrpcClientApplication.class, args);
    }

}
