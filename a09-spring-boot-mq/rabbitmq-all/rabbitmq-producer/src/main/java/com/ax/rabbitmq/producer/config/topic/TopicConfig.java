package com.ax.rabbitmq.producer.config.topic;

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
public class TopicConfig {

    public static final String TOPIC_EXCHANGE_NAME = "test.topic.exchange.name";
    public static final String TOPIC_QUEUE_NAME = "test_topic_queue_msg";
    public static final String TOPIC_ROUTING_KEY = "topic.msg";

    public static final String TOPIC_GENERAL_QUEUE_NAME = "test_topic_queue_general";
    public static final String TOPIC_GENERAL_ROUTING_KEY = "topic.#";
    //1. 交换机

    @Bean
    public TopicExchange topicExchange() {
        // durable 持久化 交换机 指定为 topic模式
        return ExchangeBuilder
                .topicExchange(TOPIC_EXCHANGE_NAME)
                .durable(false)
                .build();
// 	this(name, true, false);
//        return new TopicExchange(TOPIC_EXCHANGE_NAME, false, false);
    }

    //2 Queue 队列
    @Bean
    public Queue topicQueueA() {
        // ttl 队列 统一存活时间  .ttl(10)
        return QueueBuilder
                .durable(TOPIC_QUEUE_NAME)
                .build();
    }

    @Bean
    public Queue topicQueueB() {
        return QueueBuilder.durable(TOPIC_GENERAL_QUEUE_NAME).build();
    }


    // 普通  交换机 和 队列A 绑定
    @Bean
    public Binding bootExchangeBindingBootQueue(Queue topicQueueA,
                                                TopicExchange topicExchange) {
        return BindingBuilder
                .bind(topicQueueA)
                .to(topicExchange)
                .with(TOPIC_ROUTING_KEY);
    }

    @Bean
    public Binding bootExchangeBindingTopicQueueB(Queue topicQueueB,
                                                  TopicExchange topicExchange) {
        return BindingBuilder
                .bind(topicQueueB)
                .to(topicExchange)
                .with(TOPIC_GENERAL_ROUTING_KEY);
    }


}
