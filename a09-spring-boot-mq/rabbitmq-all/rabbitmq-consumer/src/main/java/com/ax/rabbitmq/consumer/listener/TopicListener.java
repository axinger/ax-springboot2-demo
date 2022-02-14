package com.ax.rabbitmq.consumer.listener;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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
 *  concurrency 并发
 *  containerFactory 限流
 *  ,containerFactory = "multiListenerContainer"
 * */
@Slf4j
@Component
@RabbitListener(queues = "top_queue_name")
public class TopicListener {

    @SneakyThrows
    @RabbitHandler
    public void receive(String msg, Message message, Channel channel, @Payload String body) {

//        StopWatch watch = new StopWatch();
//        watch.start();
//        log.info("receive '{}'", new String(message.getBody()));
//        watch.stop();
//        log.info("instance {} [x] Done in {}s", new String(message.getBody()), watch.getTotalTimeSeconds());

        System.out.println("body = " + body);

        //拿到消息延迟消费
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

}
