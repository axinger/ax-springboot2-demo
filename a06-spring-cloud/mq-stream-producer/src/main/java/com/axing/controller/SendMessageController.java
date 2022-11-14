package com.axing.controller;

import com.axing.model.MessageDTO;
import com.axing.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.axing.model.MessageConstant.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "消息生产者")
public class SendMessageController {

    private final MessageService messageService;

    @Operation(summary = "发送主题消息")
    @GetMapping("/orderExchange")
    public void sendMethod() {
        messageService.orderExchange();

    }

    @Operation(summary = "发送交换机消息")
    @GetMapping("/orderOut")
    public void oder() {
        messageService.orderOut();
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

    @Autowired
    private StreamBridge streamBridge;

    /**
     * 1.普通消息发送
     *
     * @param message
     */
    @PostMapping(value = "/cluster")
    public void sendClusterMsg(@RequestBody String message) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTopic(CLUSTER_MESSAGE_OUTPUT);
        messageDTO.setType("消息集群发送");
        messageDTO.setDate(LocalDateTime.now());
        messageDTO.setData(message);

        boolean result = streamBridge.send(CLUSTER_MESSAGE_OUTPUT, messageDTO);

        System.out.println(Thread.currentThread().getName() + " 消息集群发送: " + result);
    }


    /**
     * 2.广播消息发送
     */
    @PostMapping(value = "/broadcast")
    public void sendBroadcastMsg(@RequestBody Map message) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTopic(BROADCAST_MESSAGE_OUTPUT);
        messageDTO.setType("消息广播发送");
        messageDTO.setDate(LocalDateTime.now());
        messageDTO.setData(message);

        boolean result = streamBridge.send(BROADCAST_MESSAGE_OUTPUT, messageDTO);
        System.out.println(Thread.currentThread().getName() + " 消息广播发送: " + result);
    }


    @PostMapping(value = "/delay")
    public void delay() {
        Map map = new HashMap();
        map.put("date", LocalDateTime.now());


        Message<Map> message = MessageBuilder
                .withPayload(map)
                .setHeader("x-delay", 5 * 1000)
                .build();
        streamBridge.send(DELAYED_MESSAGE_OUTPUT, message);
    }
}

