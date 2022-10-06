//package com.ax.rabbitmq.consumer.listener;
//
//import com.rabbitmq.client.Channel;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.messaging.handler.annotation.Headers;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author xing
// * @version 1.0.0
// * @ClassName TopicListener.java
// * @Description TODO
// * @createTime 2022年02月16日 00:47:00
// */
//
////@Component
////@RabbitListener(queues ="zhihao.miao.order")
////public class MessageHandler {
////
////
////    @RabbitHandler
////    public void handleMessage(byte[] message){
////        System.out.println("====消费消息handleMessage");
////        System.out.println(new String(message));
////    }
////
////    @RabbitHandler
////    public void handleMessage2(String message){
////        System.out.println("====消费消息===handleMessage2");
////        System.out.println(message);
////    }
////}
//
///**
// * containerFactory
// * concurrency 并发
// * containerFactory 限流
// * ,containerFactory = "multiListenerContainer"
// */
//@Slf4j
//@Component
//public class TopicGeneralListener {
//
//
//}
