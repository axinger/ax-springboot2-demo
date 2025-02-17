package com.github.axinger.config;

import com.github.axinger.topic.Topic;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.apache.pulsar.client.api.ConsumerBuilder;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PulsarConfig {
    /**
     * 生产者,必须先注册topic
     *
     * @return
     */
    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()

                .addProducer(Topic.EXCLUSIVE_TOPIC, Map.class)
                .addProducer(Topic.FAILOVER_TOPIC, Map.class)
                .addProducer(Topic.SHARED_TOPIC, Map.class)
                .addProducer(Topic.KEY_SHARED_TOPIC, Map.class)
                .addProducer(Topic.DELIVER_AFTER_TOPIC, Map.class)
                .addProducer(Topic.DELIVER_AT_TOPIC, Map.class)
                .addProducer(Topic.testTop2, String.class)

                ;
    }

    @Bean
    public ConsumerBuilder<?> customConsumerBuilder(PulsarClient pulsarClient) {
        return pulsarClient.newConsumer()
//                .subscriptionName("test01")
                .subscriptionType(SubscriptionType.Shared)
                .receiverQueueSize(10); // 每个消费者可以缓冲的消息数
    }
}
