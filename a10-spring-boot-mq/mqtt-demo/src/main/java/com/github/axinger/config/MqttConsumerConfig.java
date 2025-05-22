package com.github.axinger.config;

import com.github.axinger.bean.MqttProperties;
import com.github.axinger.util.MqttUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MqttConsumerConfig {
    public static final String MQTT_CONSUMER_CHANNEL = "consumerMessageChannel";
    private final MqttProperties mqttProperties;
    private final MqttConfig mqttConfig;

    /**
     * mqtt输入通道: 消费者
     */
    @Bean(name = MQTT_CONSUMER_CHANNEL)
    public MessageChannel consumerMessageChannel() {
        return new DirectChannel();
    }

    //配置client,监听的topic
    @Bean
    public MessageProducer messageProducer() {
        List<String> topics = new ArrayList<>(mqttProperties.getConsumerTopic());
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        MqttUtil.getClientIdAndIp(mqttProperties) + "_consumer",
                        mqttConfig.mqttPahoClientFactory(),
                        topics.toArray(String[]::new));
        adapter.setCompletionTimeout(mqttProperties.getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(consumerMessageChannel());
//        adapter.setOutputChannelName("IoTOutput名称");
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = MQTT_CONSUMER_CHANNEL)
    public MessageHandler consumerMessageHandler() {
        return message -> {
            String topic = String.valueOf(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC));
            String msg = message.getPayload().toString();
            // 这里可以处理接收的数据
            log.info("""
                    ----------------------------接收到订阅消息 START---------------------------
                    topic:{}
                    message:{}
                    -----------------------------接收到订阅消息 END-----------------------------
                    """, topic, msg);

        };
    }

}
