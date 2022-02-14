package com.ax.rabbitmq.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName Config.java
 * @Description TODO
 * @createTime 2022年02月16日 00:03:00
 */

@Configuration
public class MqConfig {

    public static final String TOP_EXCHANGE_NAME = "top_exchange_name";
    public static final String TOP_QUEUE_NAME = "top_queue_name";

    //1. 交换机

    @Bean("bootExchange")
    public Exchange exchange() {
        // durable 持久化 交换机 指定为 topic模式
//        return ExchangeBuilder.topicExchange(TOP_EXCHANGE_NAME).durable(true).build();
// 	this(name, true, false);
        return new TopicExchange(TOP_EXCHANGE_NAME, false, false);
    }

    //2 Queue 队列
    @Bean("bootQueue")
    public Queue queue() {
        // ttl 队列 统一存活时间  .ttl(10)
        return QueueBuilder.durable(TOP_QUEUE_NAME).build();
    }
}
