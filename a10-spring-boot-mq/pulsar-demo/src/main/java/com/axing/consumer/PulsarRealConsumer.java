package com.axing.consumer;

import io.github.majusko.pulsar.annotation.PulsarConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
public class PulsarRealConsumer {

    @PulsarConsumer(topic = "bootTopic",
            clazz = Map.class)
    public void bootTopic(Map message) {
        log.info("接收到消息 time = {}, bootTopic = {}", LocalDateTime.now(), message);
    }


    @PulsarConsumer(topic = "deliverAfterTopic",
            subscriptionType = SubscriptionType.Shared,//延迟消息必须是共享模式
            clazz = Map.class)
    public void deliverAfterTopic(Map message) {
        log.info("接收到消息 time = {}, bootTopic = {}", LocalDateTime.now(), message);
    }

}