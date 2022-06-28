package com.axing.starter.auto;


import com.axing.starter.bean.HelloProperties;
import com.axing.starter.service.HelloService;
import com.axing.starter.service.impl.HelloServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/// 默认把HelloProperties 放入容器中
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        HelloService helloService = new HelloServiceImpl();
        return helloService;
    }

}
