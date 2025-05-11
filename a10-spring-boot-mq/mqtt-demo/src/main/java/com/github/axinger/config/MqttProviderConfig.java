package com.github.axinger.config;

import com.github.axinger.bean.MqttProperties;
import com.github.axinger.util.MqttUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MqttProviderConfig {

    public static final String MQTT_PROVIDER_CHANNEL = "providerMessageChannel";
    private final MqttProperties mqttProperties;
    private final MqttConfig mqttConfig;


    /**
     * 生产者消息通道。
     */
    @Bean(name = MQTT_PROVIDER_CHANNEL)
    public MessageChannel providerMessageChannel() {
        return new DirectChannel();
    }


    /**
     * 定义 Mqtt 消息处理程序 Bean。
     */
    @Bean
    @ServiceActivator(inputChannel = MQTT_PROVIDER_CHANNEL)
    public MessageHandler providerMessageHandler() {
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(MqttUtil.getClientIdAndIp(mqttProperties) + "_provider",
                mqttConfig.mqttPahoClientFactory());
        handler.setDefaultQos(1);
        handler.setDefaultRetained(false);
        handler.setAsync(false);
        handler.setAsyncEvents(false);
        return handler;
    }
}
