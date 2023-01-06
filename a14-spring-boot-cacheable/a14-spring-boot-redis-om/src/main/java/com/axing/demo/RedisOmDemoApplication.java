package com.axing.demo;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.axing.demo.*")
public class RedisOmDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisOmDemoApplication.class, args);
    }
}
