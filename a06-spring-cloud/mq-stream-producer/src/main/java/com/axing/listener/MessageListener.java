package com.axing.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

@Component
@Slf4j
public class MessageListener {


    @Value("${server.port}")
    private String serverPort;

    @Bean
    public Consumer<Message<Map<String, Object>>> myOrder() {
        return message -> {
            System.out.println("我是消费者" + serverPort);

            Object payload = message.getPayload();
            System.out.println("payload = " + payload);
            MessageHeaders headers = message.getHeaders();
            String myHeader = headers.get("myHeader", String.class);
            System.out.println("myHeader = " + myHeader);
        };
    }



}