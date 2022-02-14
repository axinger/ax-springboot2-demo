package com.ax.rabbitmq.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TtlQueueConfig.java
 * @Description TODO
 * @createTime 2022年02月17日 18:34:00
 * TTL 队列
 */
@Configuration
public class TtlQueueConfig {

    /*
     * x 普通交换机,绑定2个普通队列
     * y 死信交换机,绑定一个死信队列
     *
     * 2个普通队列发消息给 死信队列
     *
     * */


    // 普通交换机名称
    public static final String X_EXCHANGE = "x";
    // 普通队列名称
    public static final String QUEUE_A = "queue_a";
    public static final String QUEUE_A_ROUTING_KEY = "XA";

    public static final String QUEUE_B = "queue_b";
    public static final String QUEUE_B_ROUTING_KEY = "XB";

    public static final String QUEUE_C = "queue_c";
    public static final String QUEUE_C_ROUTING_KEY = "XC";

    //死信交换机名称
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String Y_DEAD_LETTER_ROUTING_KEY = "YD";
    //死信队列名称
    public static final String DEAD_LETTER_QUEUE_D = "queue_d";


    // 声明交换机
    @Bean("exchangeX")
    public DirectExchange exchangeX() {
        return new DirectExchange(X_EXCHANGE);
    }

    @Bean("queueA")
    public Queue queueA() {
        return QueueBuilder
                .durable(QUEUE_A)
                // 过期时间,单位毫秒
                .ttl(1000)
                //死信交换机
                .deadLetterExchange(Y_DEAD_LETTER_EXCHANGE)
                //死信routingKey
                .deadLetterRoutingKey(Y_DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean("queueB")
    public Queue queueB() {
        return QueueBuilder
                .durable(QUEUE_B)
                // 过期时间,单位毫秒
                .ttl(4000)
                //死信交换机
                .deadLetterExchange(Y_DEAD_LETTER_EXCHANGE)
                //死信routingKey
                .deadLetterRoutingKey(Y_DEAD_LETTER_ROUTING_KEY)
                .build();
    }



    // 死信交换机
    @Bean("exchangeY")
    public DirectExchange exchangeY() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    // 死信队列
    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder
                .durable(DEAD_LETTER_QUEUE_D)
                .build();
    }

    // 绑定

    // 普通  交换机 和 队列A 绑定
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queue,
                                  @Qualifier("exchangeX") DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(QUEUE_A_ROUTING_KEY);
    }

    // 普通  交换机 和 队列B 绑定
    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queue,
                                  @Qualifier("exchangeX") DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(QUEUE_B_ROUTING_KEY);
    }

    // 通用延迟队列
    @Bean("queueC")
    public Queue queueC() {
        return QueueBuilder
                .durable(QUEUE_C)
                // 过期时间,单位毫秒 通用延迟队列,不设置过期时间
//                .ttl(4000)
                //死信交换机
                .deadLetterExchange(Y_DEAD_LETTER_EXCHANGE)
                //死信routingKey
                .deadLetterRoutingKey(Y_DEAD_LETTER_ROUTING_KEY)
                .build();
    }
    // 通用延迟队列  交换机 和 队列C 绑定
    @Bean
    public Binding queueCBindingX(@Qualifier("queueC") Queue queue,
                                  @Qualifier("exchangeX") DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(QUEUE_C_ROUTING_KEY);
    }

    // 死信  交换机 和 死信队列D 绑定
    @Bean
    public Binding queueDBindingY(@Qualifier("queueD") Queue queueB,
                                  @Qualifier("exchangeY") DirectExchange exchangeY) {
        return BindingBuilder
                .bind(queueB)
                .to(exchangeY)
                .with(Y_DEAD_LETTER_ROUTING_KEY);
    }
}
