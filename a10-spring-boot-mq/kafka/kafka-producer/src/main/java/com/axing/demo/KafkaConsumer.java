package com.axing.demo;

import com.axing.demo.api.Topic;
import com.axing.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    // @KafkaListener(groupId = "simpleGroup_1", topics = com.ax.kafka.api.Topic.USER)
    // public void listener(ConsumerRecord<String, String> record,Acknowledgment ack) {
    //     String value = record.value();
    //     String topic = record.topic();
    //     log.info("接收到数据 topic = {},value = {}", topic, value);
    //     ack.acknowledge();
    // }

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * springboot data 方式
     *
     * @Payload：获取的是消息的消息体，也就是发送内容
     * @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)：获取发送消息的 key
     * @Header(KafkaHeaders.RECEIVED_PARTITION_ID)：获取当前消息是从哪个分区中监听到的
     * @Header(KafkaHeaders.RECEIVED_TOPIC)：获取监听的 TopicName
     * @Header(KafkaHeaders.RECEIVED_TIMESTAMP)：获取时间戳 Kafka 是通过最新保存偏移量进行消息消费的，而且确认消费的消息并不会立刻删除，所以我们可以重复的消费未被删除的数据，当第一条消息未被确认，而第二条消息被确认的时候，Kafka 会保存第二条消息的偏移量，也就是说第一条消息再也不会被监听器所获取，除非是根据第一条消息的偏移量手动获取。Kafka 的 ack 机制可以有效的确保消费不被丢失。因为自动提交是在 kafka 拉取到数据之后就直接提交，这样很容易丢失数据，尤其是在需要事物控制的时候。
     */
    @SneakyThrows
    @KafkaListener(id = "pullPatternMsg", topicPattern = Topic.USER, concurrency = "1")
    // @DependsOn("KafkaDataRouteConsumer")
    public void pullPatternMsg(@Payload String data,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               // @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) ByteBuffer key,
                               Acknowledgment ack,// 手动提交offset
                               // @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) long offSet,
                               Consumer<?, ?> consumer// 消费者
    ) {

        User user = objectMapper.readValue(data, User.class);
        log.info("接收到数据 topic = {},value = {}", topic, user);

        ack.acknowledge();
        // 同步发送ack
        // consumer.commitAsync();
    }
}
