package com.github.axinger.config;

import com.github.axinger.model.TestJsonBean;
import lombok.extern.slf4j.Slf4j;
import org.dromara.mica.mqtt.codec.MqttPublishMessage;
import org.dromara.mica.mqtt.codec.MqttQoS;
import org.dromara.mica.mqtt.core.deserialize.MqttJsonDeserializer;
import org.dromara.mica.mqtt.spring.client.MqttClientSubscribe;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class MqttClientSubscribeListener {

    @MqttClientSubscribe("/test/#")
    public void subQos0(String topic, byte[] payload) {
        log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
    }

    @MqttClientSubscribe(value = "/qos1/#", qos = MqttQoS.QOS1)
    public void subQos1(String topic, byte[] payload) {
        log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
    }

    @MqttClientSubscribe("/sys/${productKey}/${deviceName}/thing/sub/register")
    public void thingSubRegister(String topic, byte[] payload) {
        // 1.3.8 开始支持，@MqttClientSubscribe 注解支持 ${} 变量替换，会默认替换成 +
        // 注意：mica-mqtt 会先从 Spring boot 配置中替换参数 ${}，如果存在配置会优先被替换。
        log.info("topic:{} payload:{}", topic, new String(payload, StandardCharsets.UTF_8));
    }

    @MqttClientSubscribe(
            value = "/test/json",
            deserialize = MqttJsonDeserializer.class // 2.4.5 开始支持 自定义序列化，默认 json 序列化
    )
    public void testJson(String topic, MqttPublishMessage message, TestJsonBean data) {
        // 2.4.5 开始支持，支持 2 到 3 个参数，字段类型映射规则如下
        // String 字符串会默认映射到 topic，
        // MqttPublishMessage 会默认映射到 原始的消息，可以拿到 mqtt5 的 props 参数
        // byte[] 会映射到 mqtt 消息内容 payload
        // ByteBuffer 会映射到 mqtt 消息内容 payload
        // 其他类型会走序列化，确保消息能够序列化，默认为 json 序列化
        log.info("topic:{} json data:{}", topic, data);
    }

}
