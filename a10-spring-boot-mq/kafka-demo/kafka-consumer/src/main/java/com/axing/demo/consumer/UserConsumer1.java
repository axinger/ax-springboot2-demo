package com.axing.demo.consumer;

import com.axing.demo.api.Topic;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UserConsumer1 {


    @Autowired
    private ObjectMapper objectMapper;


    // 注意：kafkaListenerContainerFactory 是一个支持手动确认和异步提交偏移量的自定义工厂。
    // 如果您使用的是默认工厂，则将自动确认和同步提交偏移量。
    @KafkaListener(id = "ax_kafka_demo1",
            topics = Topic.USER_DTO_JSON,
            groupId = "my_group_id1"
//            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(
            // ConsumerRecord<String, User> record,
            GenericMessage<String> message,
            @Payload String data,
//            @Payload UserDTO userDTO,
            Acknowledgment ack,
//            ConsumerRecord<?, UserDTO> record,
            Consumer<?, ?> consumer) {
        log.info("ax_kafka_demo1==================================================");
        try {
            // 模拟业务处理
            // handleBusiness(record);

            // 获取主题名称
            // String topic = record.topic();
            //
            // // 获取主题分区 ID
            // int partition = record.partition();
            //
            // // 获取偏移量
            // long offset = record.offset();
            //
            // // 获取键和值
            // String key = record.key();
            // User value = record.value();
            // log.info("Received message. Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}", topic, partition, offset, key, value);
            // log.info("解析data = {}", data);

//            ConsumerUser user = objectMapper.readValue(data, ConsumerUser.class);
//            log.info("解析data objectMapper user = {}", user);
            System.out.println("message.getPayload() = " + message.getPayload());
            System.out.println("data = " + data);
//            System.out.println("userDTO = " + userDTO);

//            UserDTO value = record.value();
//            System.out.println("value = " + value);

//            System.out.println("message = " + message);
            // 手动确认消息状态，并异步提交最新的消息偏移量。
            ack.acknowledge();
            consumer.commitAsync((offsets, exception) -> {
                if (exception != null) {
                    log.error("提交偏移量失败 {}", exception.getMessage(), exception);
                } else {
                    log.info("提交偏移量成功 {}", offsets.keySet());
                }
            });
        } catch (Exception e) {
            log.error("Failed to process message: {} ", e.getMessage());
            // 处理失败的情况，可以根据具体需求重试或将其发送到 DLQ 等操作
        }
    }

}
