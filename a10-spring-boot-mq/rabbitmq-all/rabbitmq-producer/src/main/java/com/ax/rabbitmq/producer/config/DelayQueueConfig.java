package com.ax.rabbitmq.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName DelaQueueConfig.java
 * @Description TODO
 * @createTime 2022年02月17日 20:30:00
 */
@Configuration
public class DelayQueueConfig {

    // 交换机
    public static final String DELAY_EXCHANGE = "delay.exchange";
    // 队列
    public static final String DELAY_QUEUE = "delay.queue";
    // key
    public static final String DELAY_ROUTING_KEY = "delay.routing.key";


    // 声明交换机
    @Bean("delayExchange")
    public CustomExchange delayExchange() {

        //创建一个自定义交换机，可以发送延迟消息
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean("delayQueue")
    public Queue delayQueue() {
        return QueueBuilder
                .durable(DELAY_QUEUE)
                .build();
    }

    @Bean
    public Binding delayQueueBinding(Queue delayQueue,
                                     CustomExchange delayExchange) {
        return BindingBuilder
                .bind(delayQueue)
                .to(delayExchange)
                .with(DELAY_ROUTING_KEY)
                .noargs();
    }

}
