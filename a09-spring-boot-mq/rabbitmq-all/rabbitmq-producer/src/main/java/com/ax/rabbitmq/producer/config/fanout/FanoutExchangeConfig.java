package com.ax.rabbitmq.producer.config.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形交换机,发布订阅模式
 */
@Configuration
public class FanoutExchangeConfig {


    public static final String EXCHANGE_NAME = "test.fanout.exchange.name";
    public static final String QUEUE_NAME_A = "test_fanout_queue_A";

    public static final String QUEUE_NAME_B = "test_fanout_queue_B";

    /**
     * 扇形,广播交换器
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME, false, false);
    }

    //AnonymousQueue类型的队列，它的名字是由客户端生成的，而且是非持久的，独占的，自动删除的队列
    @Bean
    public Queue publishQueue1() {
        return QueueBuilder
                .durable(QUEUE_NAME_A)
                .build();
    }

    @Bean
    public Queue publishQueue2() {
        return QueueBuilder
                .durable(QUEUE_NAME_B)
                .build();
    }

    //队列和交换机绑定
    //这种关系可以读作：这个队列对这个交换器里的消息感兴趣。
    //虽然 Queue类型有多个实例，但spring会自动更加名字匹配，bean名字匹配参数名字
    @Bean
    public Binding binding1(FanoutExchange fanout, Queue publishQueue1) {
        return BindingBuilder.bind(publishQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue publishQueue2) {
        return BindingBuilder.bind(publishQueue2).to(fanout);
    }
}
