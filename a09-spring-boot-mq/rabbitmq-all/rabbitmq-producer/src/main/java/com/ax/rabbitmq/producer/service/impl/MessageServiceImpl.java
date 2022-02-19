package com.ax.rabbitmq.producer.service.impl;

import com.ax.rabbitmq.producer.config.topic.TopicConfig;
import com.ax.rabbitmq.producer.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageServiceImpl implements IMessageService{


    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Override
    public void sendMessage(Object object) {


        // 消息单独过期时间
        final MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setExpiration("5000"); // 默认毫秒
            return message;
        };
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME, "test_topic.msg", object,messagePostProcessor);

        // 带参数
//        CorrelationData correlationData = new CorrelationData("1");
//        rabbitTemplate.convertAndSend(MqConfig.TOP_EXCHANGE_NAME, "boot.msg", object,messagePostProcessor,correlationData);
    }




}
