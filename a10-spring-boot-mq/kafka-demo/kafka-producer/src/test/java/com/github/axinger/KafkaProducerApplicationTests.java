package com.github.axinger;

import com.github.axinger.api.Topic;
import com.github.axinger.model.ProducerUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@Slf4j
class KafkaProducerApplicationTests {


    @Autowired
    private KafkaTemplate<String, ProducerUser> kafkaTemplate;

    @SneakyThrows
    @Test
    void test1() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ProducerUser user = new ProducerUser();
        user.setLastName("jim");
        user.setAge(11);
        user.setBirthday(LocalDateTime.now());
        ListenableFuture<SendResult<String, ProducerUser>> send = kafkaTemplate.send(Topic.USER_JSON, user);

        send.addCallback(sendResult -> {
            log.info("发送消息成功===================");
            RecordMetadata recordMetadata = null;
            if (sendResult != null) {
                recordMetadata = sendResult.getRecordMetadata();
            }
            System.out.println("recordMetadata = " + recordMetadata);
            ProducerUser value = null;
            if (sendResult != null) {
                value = sendResult.getProducerRecord().value();
            }
            System.out.println("value = " + value);
            countDownLatch.countDown();
        }, e -> {
            log.error("发送消息失败,{}", e.getMessage());
            countDownLatch.countDown();
        });


        countDownLatch.await();
        System.out.println("=================================");
    }

}
