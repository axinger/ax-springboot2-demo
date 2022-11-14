package com.axing.consumer;

import com.axing.api.MessageConsumerApi;
import com.axing.model.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class MessageConsumerImpl implements MessageConsumerApi {


    @Value("${server.port}")
    private String serverPort;

    @Bean
    @Override
    public Consumer<Message<MessageDTO<Object>>> order() {
        return message -> {
            System.out.println("order 接收消息为" + message);
            System.out.println("我是消费者" + serverPort);
            MessageDTO<Object> payload = message.getPayload();
            System.out.println("payload = " + payload);
            MessageHeaders headers = message.getHeaders();
            String userId = headers.get(AmqpHeaders.USER_ID, String.class);
            System.out.println("userId = " + userId);
        };
    }


    @Bean
    @Override
    public Consumer<String> sms() {
        return message -> {
            System.out.println("sms 我是消费者" + serverPort);
            System.out.println("Received message " + message);
        };
    }

    /**
     * 函数式编辑接收消息
     */
    @Bean
    @Override
    public Consumer<String> cluster() {
        return message -> {
            System.out.println("随即被几个消费者消费，一个消息只会被消费一次:接收的集群消息为：" + message);
        };
    }

    /**
     * 函数式编辑接收消息
     */
    @Bean
    @Override
    public Consumer<String> broadcast() {
        return message -> {
            System.out.println("接收的广播消息为：" + message);
        };
    }

    @Bean
    @Override
    public Consumer<String> delayed() {
        return message -> {
            log.info("延迟消息为：" + message);
        };
    }
}