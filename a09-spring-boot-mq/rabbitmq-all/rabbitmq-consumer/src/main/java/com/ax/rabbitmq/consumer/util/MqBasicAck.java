package com.ax.rabbitmq.consumer.util;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName MqBasicAck.java
 * @Description TODO
 * @createTime 2022年02月19日 18:19:00
 */

public class MqBasicAck {

    @SneakyThrows
    /**
     * 手动确认消息
     * */
    public static void basicAck(Channel channel, Message message) {
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
    }
}
