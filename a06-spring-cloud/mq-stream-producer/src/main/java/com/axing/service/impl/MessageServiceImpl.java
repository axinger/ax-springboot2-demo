package com.axing.service.impl;

import com.axing.model.MessageDTO;
import com.axing.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final StreamBridge streamBridge;


    public void oder() {
        Map map = new HashMap();
        map.put("type", "订单业务");
        map.put("date", LocalDateTime.now());

        Message<Map> message = MessageBuilder
                .withPayload(map)
                .setHeader("myHeader", "abc123")
                .build();

        boolean send = streamBridge.send("orderExchange", message);
        System.out.println("send = " + send);
    }

    @Override
    public void orderExchange() {

        //这里说明一下这个 streamBridge.send 方法的参数 第一个参数是exchange或者topic 就是主题名称
        //默认的主题名称是通过
        //输入:    <方法名> + -in- + <index>
        //输出:    <方法名> + -out- + <index>
        //这里我们接收的时候就要用send方法 参数是consumer<String>接收  详情看8802的controller
        //consumer的参数类型是这里message的类型


        MessageDTO<Object> dto = new MessageDTO<>();
        dto.setTopic("mq.order");
        dto.setType("exchange");
        dto.setDate(LocalDateTime.now());
        dto.setData("orderExchange交换机消息");

        Message<MessageDTO<Object>> message = MessageBuilder
                .withPayload(dto)
                .setHeader("myHeader", "abc123")
                .build();


//        boolean send = streamBridge.send("myOrder-out-0", message);
//        boolean send = streamBridge.send("orderExchange", message);


        boolean send = streamBridge.send("mq.order", message);
        System.out.println("发送主题消息是否成功send = " + send);
    }


    @Override
    public void orderOut() {

        //这里说明一下这个 streamBridge.send 方法的参数 第一个参数是exchange或者topic 就是主题名称
        //默认的主题名称是通过
        //输入:    <方法名> + -in- + <index>
        //输出:    <方法名> + -out- + <index>
        //这里我们接收的时候就要用send方法 参数是consumer<String>接收  详情看8802的controller
        //consumer的参数类型是这里message的类型

        Map map = new HashMap();
        map.put("type", "myOrder-out-0,主题消息");
        map.put("date", LocalDateTime.now());

        Message<Map> message = MessageBuilder
                .withPayload(map)
                .setHeader("myHeader", "abc123")
                .build();

//        boolean send = streamBridge.send("myOrder-out-0", message);
        boolean send = streamBridge.send("order-out-0", message);
        System.out.println("send = " + send);
    }


}
