package com.axing.api;

import com.axing.model.MessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

public interface MessageConsumerApi {

    @Bean
    Consumer<Message<MessageDTO<Object>>> order();

    @Bean
    Consumer<String> sms();

    /**
     * 函数式编辑接收消息
     */
    @Bean
    Consumer<String> cluster();

    /**
     * 函数式编辑接收消息
     */
    @Bean
    Consumer<String> broadcast();

    @Bean
    Consumer<String> delayed();
}
