package com.axing.demo.consumer;

import com.axing.demo.api.Topic;
import com.axing.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class DemoConsumer {


    // @KafkaListener(groupId = "simpleGroup_1", topics = com.ax.kafka.api.Topic.USER)
    // public void listener(ConsumerRecord<String, String> record,Acknowledgment ack) {
    //     String value = record.value();
    //     String topic = record.topic();
    //     log.info("接收到数据 topic = {},value = {}", topic, value);
    //     ack.acknowledge();
    // }

    @Autowired
    private ObjectMapper objectMapper;

    // /**
    //  * springboot data 方式
    //  *
    //  * @Payload：获取的是消息的消息体，也就是发送内容
    //  * @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)：获取发送消息的 key
    //  * @Header(KafkaHeaders.RECEIVED_PARTITION_ID)：获取当前消息是从哪个分区中监听到的
    //  * @Header(KafkaHeaders.RECEIVED_TOPIC)：获取监听的 TopicName
    //  * @Header(KafkaHeaders.RECEIVED_TIMESTAMP)：获取时间戳 Kafka 是通过最新保存偏移量进行消息消费的，而且确认消费的消息并不会立刻删除，所以我们可以重复的消费未被删除的数据，当第一条消息未被确认，而第二条消息被确认的时候，Kafka 会保存第二条消息的偏移量，也就是说第一条消息再也不会被监听器所获取，除非是根据第一条消息的偏移量手动获取。Kafka 的 ack 机制可以有效的确保消费不被丢失。因为自动提交是在 kafka 拉取到数据之后就直接提交，这样很容易丢失数据，尤其是在需要事物控制的时候。
    //  */
    // @SneakyThrows
    // @KafkaListener(id = "pullPatternMsg", topicPattern = Topic.USER, concurrency = "1" )
    // // @DependsOn("KafkaDataRouteConsumer")
    // public void pullPatternMsg(@Payload String data,
    //                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
    //                            // @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) ByteBuffer key,
    //                            Acknowledgment ack,// 手动提交offset
    //                            // @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
    //                            @Header(KafkaHeaders.OFFSET) long offSet,
    //                            Consumer<?, ?> consumer// 消费者
    // ) {
    //
    //     User user = objectMapper.readValue(data, User.class);
    //     log.info("接收到数据 topic = {},value = {}", topic, user);
    //
    //     ack.acknowledge();
    //     // 同步发送ack
    //     // consumer.commitAsync();
    // }


    // 注意：kafkaListenerContainerFactory 是一个支持手动确认和异步提交偏移量的自定义工厂。
    // 如果您使用的是默认工厂，则将自动确认和同步提交偏移量。
    @KafkaListener(id = "ax_kafka_demo1",
            topics = Topic.USER,
            groupId = "my_group_id1",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, String> record,
                       @Payload String data, Acknowledgment ack,
                       Consumer<?, ?> consumer) {
        log.info("ax_kafka_demo1==================================================");
        try {
            // 模拟业务处理
            // handleBusiness(record);

            // 获取主题名称
            String topic = record.topic();

            // 获取主题分区 ID
            int partition = record.partition();

            // 获取偏移量
            long offset = record.offset();

            // 获取键和值
            String key = record.key();
            String value = record.value();
            log.info("Received message. Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}", topic, partition, offset, key, value);
            log.info("解析data = {}", data);

            User user = objectMapper.readValue(data, User.class);
            log.info("解析data user = {}", user);
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
