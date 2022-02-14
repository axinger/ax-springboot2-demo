package com.ax.demo.config.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by macro on 2020/5/19.
 */
@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue hello() {
        return new Queue("simple.hello");
    }

    @Bean
    public SimpleSender simpleSender() {
        return new SimpleSender();
    }

    @Bean
    public SimpleReceiver simpleReceiver() {
        return new SimpleReceiver();
    }

}
