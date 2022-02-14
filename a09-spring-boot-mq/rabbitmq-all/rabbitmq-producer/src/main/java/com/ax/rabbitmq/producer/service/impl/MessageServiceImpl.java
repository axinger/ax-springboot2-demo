package com.ax.rabbitmq.producer.service.impl;

import com.ax.rabbitmq.producer.config.MqConfig;
import com.ax.rabbitmq.producer.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageServiceImpl implements IMessageService, ConfirmCallback, ReturnCallback {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Object object) {

        /**
         * correlationData 相关配置
         * ack 是否成功收到消息
         * cause 失败原因
         * */
        rabbitTemplate.setConfirmCallback(this);

        /**
         * 回退模式
         * 发送给交换机后, 路由到queue失败,才会执行
         * 处理消息模式
         *  1.丢弃,默认
         *  2.返回给发送方
         *
         *  事务方式 性能较差
         * */

        /**
         * 设置处理模式
         * */
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(this);
        // 消息单独过期时间
        final MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setExpiration("5000"); // 默认毫秒
            return message;
        };
        rabbitTemplate.convertAndSend(MqConfig.TOP_EXCHANGE_NAME, "boot.msg", object,messagePostProcessor);


    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("---- returnedMessage ----replyCode=" + replyCode + " replyText=" + replyText + " ");
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("---- confirm ----ack=" + ack + "  cause=" + String.valueOf(cause));
        log.info("correlationData -->" + correlationData.toString());
        if (ack) {
            log.info("---- confirm ----ack==true  cause=" + cause);
        } else {
            log.info("---- confirm ----ack==false  cause=" + cause);
        }
    }

}
