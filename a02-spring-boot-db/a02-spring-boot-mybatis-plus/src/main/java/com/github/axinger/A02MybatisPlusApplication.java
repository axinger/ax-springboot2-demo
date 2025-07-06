package com.github.axinger;

import com.github.axinger.config.TenantProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(value = {"com.github.axinger.mapper"})
@EnableConfigurationProperties(value = {
        TenantProperties.class,
})
@EnableTransactionManagement
public class A02MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(A02MybatisPlusApplication.class, args);
    }

}
