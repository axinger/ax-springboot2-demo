package com.ax.demo.config.publish;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by macro on 2020/5/19.
 */
@Configuration
public class PublishRabbitConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue fanoutQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding fanoutBinding1(FanoutExchange fanout, Queue fanoutQueue1) {
        return BindingBuilder.bind(fanoutQueue1).to(fanout);
    }

    @Bean
    public Binding fanoutBinding2(FanoutExchange fanout, Queue fanoutQueue2) {
        return BindingBuilder.bind(fanoutQueue2).to(fanout);
    }

    @Bean
    public PublishReceiver fanoutReceiver() {
        return new PublishReceiver();
    }

    @Bean
    public PublishSender fanoutSender() {
        return new PublishSender();
    }

}
