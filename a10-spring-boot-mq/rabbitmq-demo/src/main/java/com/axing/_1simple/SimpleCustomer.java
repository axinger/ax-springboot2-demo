package com.axing._1simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 方式1.简单模式
 * 一个生产者对应一个消费者！！
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
@Slf4j
public class SimpleCustomer {
    @RabbitHandler
    public void receive(String message) {
        log.info("方式1.简单模式message== {}", message);
    }
}