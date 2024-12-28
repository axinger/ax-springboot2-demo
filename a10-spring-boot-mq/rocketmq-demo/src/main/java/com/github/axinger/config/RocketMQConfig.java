package com.github.axinger.config;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQConfig {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Bean
    public RocketMQLocalTransactionListener transactionListener() {
        return new MyTransactionListener(rocketMQTemplate);
    }


}
