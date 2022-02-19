package com.ax.rabbitmq.consumer.listener;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName TopicListener.java
 * @Description TODO
 * @createTime 2022年02月16日 00:47:00
 */

//@Component
//@RabbitListener(queues ="zhihao.miao.order")
//public class MessageHandler {
//
//
//    @RabbitHandler
//    public void handleMessage(byte[] message){
//        System.out.println("====消费消息handleMessage");
//        System.out.println(new String(message));
//    }
//
//    @RabbitHandler
//    public void handleMessage2(String message){
//        System.out.println("====消费消息===handleMessage2");
//        System.out.println(message);
//    }
//}

/**
 * containerFactory
 * concurrency 并发
 * containerFactory 限流
 * ,containerFactory = "multiListenerContainer"
 */
@Slf4j
@Component

public class TopicListener {
    /**
     * @Payload String body 只能是string 序列化string
     */
    @SneakyThrows
    @RabbitListener(queues = "test_topic_queue_msg")
    public void receive(Message message, Channel channel, @Payload String body) {
        //拿到消息延迟消费
        TimeUnit.SECONDS.sleep(2);
        System.out.println("test_topic_queue 专用队列====================");
//        System.out.println("message = " + message.getBody());
        System.out.println("body = " + body);

        System.out.println("\n\n");
//        @Headers Map<String, Object> headers
//        System.out.println("headers = " + headers);

        final long deliveryTag = message.getMessageProperties().getDeliveryTag();

        channel.basicAck(deliveryTag, true);
//        try {
//
//            channel.basicAck(deliveryTag, false);
//            System.out.println("get msg2 success msg = " + msg);
//
//        } catch (Exception e) {
//            //消费者处理出了问题，需要告诉队列信息消费失败 b1: 重回队列
//            channel.basicNack(deliveryTag, false, true);
//            System.err.println("get msg2 failed msg = " + msg);
//        }

    }

    /**
     * @Payload String body 只能是string 序列化string
     */
    @SneakyThrows
    @RabbitListener(queues = "test_topic_queue_general")
    public void receive2(Message message, Channel channel, @Payload String body, @Headers Map<String, Object> headers) {
        //拿到消息延迟消费
        TimeUnit.SECONDS.sleep(3);

        System.out.println("test_topic_queue_general 通用队列====================");
        System.out.println("body = " + body);
        System.out.println("\n\n");
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);

    }

}
