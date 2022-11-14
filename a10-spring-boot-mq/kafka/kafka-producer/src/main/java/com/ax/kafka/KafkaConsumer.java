package com.ax.kafka;

import com.ax.kafka.api.Topic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {

    @KafkaListener(groupId = "simpleGroup_1", topics = com.ax.kafka.api.Topic.SIMPLE)
    public void listener(ConsumerRecord<?, ?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("数据接收完毕："+message);
        }
    }
}
