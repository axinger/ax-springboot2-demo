package com.github.axinger;

import com.github.axinger.api.Topic;
import com.github.axinger.api.model.UserDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
@Slf4j
class KafkaProducerApplicationTests {


    @Autowired
    private KafkaTemplate<String, UserDTO> kafkaTemplate;

    @SneakyThrows
    @Test
    void test1() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        UserDTO user = new UserDTO();
        user.setName("jim");
        user.setAge(11);
        user.setBirthday(LocalDateTime.now());

        CompletableFuture<SendResult<String, UserDTO>> sent = kafkaTemplate.send(Topic.TEST_01, user);


        sent.whenComplete((result, e) -> {
            if (e != null) {
                log.error("发送消息失败,{}", e.getMessage());
                countDownLatch.countDown();
                return;
            }
            log.info("发送消息成功===================");
            RecordMetadata recordMetadata = null;
            UserDTO value = null;
            if (result != null) {
                recordMetadata = result.getRecordMetadata();
                value = result.getProducerRecord().value();
            }
            System.out.println("偏移量 = " + recordMetadata);
            System.out.println("生产消息 = " + value);

            countDownLatch.countDown();
        });


//        ListenableFuture<SendResult<String, ProducerUser>> send = kafkaTemplate.send(Topic.USER_JSON, user);
//
//        send.addCallback(sendResult -> {
//            log.info("发送消息成功===================");
//            RecordMetadata recordMetadata = null;
//            if (sendResult != null) {
//                recordMetadata = sendResult.getRecordMetadata();
//            }
//            System.out.println("recordMetadata = " + recordMetadata);
//            ProducerUser value = null;
//            if (sendResult != null) {
//                value = sendResult.getProducerRecord().value();
//            }
//            System.out.println("value = " + value);
//            countDownLatch.countDown();
//        }, e -> {
//            log.error("发送消息失败,{}", e.getMessage());
//            countDownLatch.countDown();
//        });


        countDownLatch.await();
        System.out.println("=================================");
    }

}
