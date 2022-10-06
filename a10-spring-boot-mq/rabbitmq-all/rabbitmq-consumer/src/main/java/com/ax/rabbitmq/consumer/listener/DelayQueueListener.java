package com.ax.rabbitmq.consumer.listener;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName DelayQueueListener.java
 * @Description TODO
 * @createTime 2022年02月17日 20:42:00
 */
@Component
@RabbitListener(queues = "delay.queue")
public class DelayQueueListener {

    @SneakyThrows
    @RabbitHandler()
    public void doSth(Channel channel, Message message, @Payload String body) {
        System.out.println("body = " + body);

        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
    }
}
