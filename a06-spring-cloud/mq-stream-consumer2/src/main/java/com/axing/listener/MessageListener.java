package com.axing.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

@Component
@Slf4j
public class MessageListener {


    @Value("${server.port}")
    private String serverPort;

//    @Bean
//    public Consumer<Message<Map<String, Object>>> myOrder() {
//        return message -> {
//            System.out.println("我是消费者" + serverPort);
//
//            Object payload = message.getPayload();
//            System.out.println("payload = " + payload);
//            MessageHeaders headers = message.getHeaders();
//            String myHeader = headers.get("myHeader", String.class);
//            System.out.println("myHeader = " + myHeader);
//        };
//    }

    /**
     * demoChannel 是管道的名字
     * @return
     */
    @Bean
    public Consumer<String> order() {
        return message -> {
            log.info("接收消息为：{}", message);
        };
    }

    @Bean
    public Consumer<String> sms() {
        return message -> {
            System.out.println("我是消费者" + serverPort);
            System.out.println("Received message " + message);
        };
    }

    /**
     * 函数式编辑接收消息
     */
    @Bean
    public Consumer<String> cluster() {
        return message -> {
            System.out.println("随即被几个消费者消费，一个消息只会被消费一次:接收的集群消息为：" + message);
        };
    }

    /**
     * 函数式编辑接收消息
     */
    @Bean
    public Consumer<String> broadcast() {
        return message -> {
            System.out.println("接收的广播消息为：" + message);
        };
    }

    @Bean
    public Consumer<String> delayed() {
        return message -> {
            log.info("延迟消息为：" + message);
        };
    }


}