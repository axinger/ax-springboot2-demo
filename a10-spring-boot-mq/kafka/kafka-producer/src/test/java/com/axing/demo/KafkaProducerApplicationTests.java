package com.axing.demo;

import com.axing.demo.api.Topic;
import com.axing.demo.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class KafkaProducerApplicationTests {


    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @SneakyThrows
    @Test
    void test1() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        User user = new User();
        user.setLastName("jim");
        user.setAge(11);
        user.setBirthday(LocalDateTime.now());
        ListenableFuture<SendResult<String, User>> send = kafkaTemplate.send(Topic.USER, user);

        send.addCallback(sendResult -> {
            log.info("发送消息成功===================");
            RecordMetadata recordMetadata = null;
            if (sendResult != null) {
                recordMetadata = sendResult.getRecordMetadata();
            }
            System.out.println("recordMetadata = " + recordMetadata);
            User value = null;
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
