package com.axing.controller;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SendMessageController {

    //这里直接装配一个桥 用来连接rabbit或者kafka
    @Autowired
    StreamBridge streamBridge;

    @RequestMapping("/send")
    public void sendMethod() {
        String uuid = UUID.randomUUID().toString();
        //这里说明一下这个 streamBridge.send 方法的参数 第一个参数是exchange或者topic 就是主题名称
        //默认的主题名称是通过
        //输入:    <方法名> + -in- + <index>
        //输出:    <方法名> + -out- + <index>
        //这里我们接收的时候就要用send方法 参数是consumer<String>接收  详情看8802的controller
        //consumer的参数类型是这里message的类型

        Map map = new HashMap();
        map.put("data", uuid);

        boolean send = streamBridge.send("send-in-0", map);
        System.out.println("send = " + send);
    }

    @RequestMapping("/order")
    public void oder() {
        Map map = new HashMap();
        map.put("type", "订单业务");
        map.put("date", LocalDateTime.now());

        Message<Map> message = MessageBuilder
                .withPayload(map)
                .setHeader("myHeader", "abc123")
                .build();

        boolean send = streamBridge.send("mq.order", message);
        System.out.println("send = " + send);
    }

    @RequestMapping("/sms")
    public void sms() {
        Map map = new HashMap();
        map.put("type", "发送短信业务");
        map.put("date", LocalDateTime.now());
        boolean send = streamBridge.send("mq.sms", map);
        System.out.println("send = " + send);
    }

//    @RequestMapping("/send2")
//    public void sendMethod2() {
//        String uuid = UUID.randomUUID().toString();
//        //这里说明一下这个 streamBridge.send 方法的参数 第一个参数是exchange或者topic 就是主题名称
//        //默认的主题名称是通过
//        //输入:    <方法名> + -in- + <index>
//        //输出:    <方法名> + -out- + <index>
//        //这里我们接收的时候就要用send方法 参数是consumer<String>接收  详情看8802的controller
//        //consumer的参数类型是这里message的类型
//
//
//        Map map = new HashMap();
//        map.put("data",uuid);
//
//        Message<Map> message = MessageBuilder
//                .withPayload(map)
//                .setHeader(AmqpHeaders.PUBLISH_CONFIRM_CORRELATION, "myHeader")
//                .build();
//        streamBridge.send("sendMessage-in-0", message);
//    }
}

