package com.axing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

@Component
public class ReceiveMessageController {


    @Value("${server.port}")
    private String serverPort;

    @Bean
    public Consumer<Message<Map<String, Object>>> order() {
        return message -> {
            System.out.println("我是消费者" + serverPort);

            Object payload = message.getPayload();
            System.out.println("payload = " + payload);
            MessageHeaders headers = message.getHeaders();
            String myHeader = headers.get("myHeader", String.class);
            System.out.println("myHeader = " + myHeader);
        };
    }

    @Bean
    public Consumer<String> sms() {
        return message -> {
            System.out.println("我是消费者" + serverPort);
            System.out.println("Received message " + message);
        };
    }
}