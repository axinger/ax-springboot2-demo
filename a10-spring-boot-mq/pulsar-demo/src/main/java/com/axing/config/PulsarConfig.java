package com.axing.config;

import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PulsarConfig {
    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
                .addProducer("bootTopic", Map.class)
                .addProducer("stringTopic", String.class)
                .addProducer("deliverAfterTopic", Map.class)

                ;
    }
}