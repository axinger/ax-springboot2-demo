package com.github.axinger.mqtt;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Mqtt5Route extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("paho-mqtt5:testTopic?qos=2")
                .log("MQTT连接建立成功，开始监听testTopic主题")
                .log("Received MQTT message: ${body}")
                .process(exchange -> {
                    String message = exchange.getIn().getBody(String.class);
                    System.out.println("从MQTT订阅消息 Processing message: " + message);
                    // 打印消息头信息
                    System.out.println("消息头: " + exchange.getIn().getHeaders());

//                    exchange.getContext().stop();


                    // 发送处理确认
//                    String ackTopic = "ack/" + exchange.getIn().getHeader("MqttMessageId");
//                    producerTemplate.sendBody("paho-mqtt5:" + ackTopic, "ACK");
                })
                .onException(Exception.class)
                .log("MQTT消息处理异常: ${exception.message}")
//                .handled(true)
                .end();
    }
}
