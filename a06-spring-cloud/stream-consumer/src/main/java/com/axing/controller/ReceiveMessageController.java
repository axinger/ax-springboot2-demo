package com.axing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

@Component
public class ReceiveMessageController {

    @Value("${server.port}")
    private String serverPort;

    @Bean(value = "send")
    //这里接收rabbitmq的条件是参数为Consumer 并且 方法名和supplier方法名相同
    //这里的返回值是一个匿名函数 返回类型是consumer 类型和提供者的类型一致
    //supplier发送的exchange是 send-in-0 这里只需要用send方法名即可
    Consumer<Map> send() {
        return str -> {
            System.out.println("我是消费者"+serverPort+"，我收到了消息："+str);
        };
    }



//    //Supplier声明一个消息生产者，source就对应source-out-0的生产者binding
//    @Bean
//    public Supplier<String> source(){
//        return ()->{
//            String message = "FromSource1";
//            System.out.println("******************");
//            System.out.println("From Source1");
//            System.out.println("******************");
//            System.out.println("Sending value: " + message);
//            return message;
//        };
//    }
//
//    //Function方式声明binding，相当于同时声明了一个Producer的Bindng和一个Consumer的Binding。transfer-in-0 表示消费者  transfer-out-0 表示生产者
//    //其作用就在于，echo-in-0收到的消息，立即就会通过echo-out-0发送出去。
//    @Bean
//    public Function<String,String> transfer(){
//        return message -> {
//            System.out.println("transfer: "+message);
//            return "transfer:"+message;
//        };
//    }

    //Consumer声明一个消息消费者，sink1就对应sink-in-0
//    @Bean
//    public Consumer<String> sink() {
//        return message -> {
//            System.out.println("******************");
//            System.out.println("At Sink1");
//            System.out.println("******************");
//            System.out.println("Received message " + message);
//        };
//    }
    @Bean
    public Consumer<Message<Map<String, Object>>> order() {
        return message -> {
            System.out.println("我是消费者" + serverPort);
            Object payload = message.getPayload();
            System.out.println("payload = " + payload);
            MessageHeaders headers = message.getHeaders();
            String myHeader = headers.get("myHeader", String.class);
            System.out.println("myHeader = " + myHeader);
        };
    }
    @Bean
    public Consumer<String> sms() {
        return message -> {
            System.out.println("我是消费者" + serverPort);
            System.out.println("Received message " + message);
        };
    }


}