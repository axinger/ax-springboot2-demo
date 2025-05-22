package com.github.axinger.controller;

import com.github.axinger.api.Topic;
import com.github.axinger.api.model.UserDTO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @Autowired
    private KafkaTemplate<String, UserDTO> kafkaTemplate;

    @SneakyThrows
    @GetMapping("/test1")
    void test1() {


        UserDTO user = new UserDTO();
        user.setName("jim");
        user.setAge(11);
        user.setBirthday(LocalDateTime.now());

        ListenableFuture<SendResult<String, UserDTO>> sent = kafkaTemplate.send(Topic.TEST_01, user);


        // 添加回调以处理发送结果
        sent.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, UserDTO> result) {

                System.out.println("发送成功result = " + result);
            }


            @Override
            public void onFailure(Throwable ex) {
                System.err.println("发送失败: " + ex.getMessage());
            }
        });

//        sent.completable((result, e) -> {
//            if (e != null) {
//                log.error("发送消息失败,{}", e.getMessage());
//                countDownLatch.countDown();
//                return;
//            }
//            log.info("发送消息成功===================");
//            RecordMetadata recordMetadata = null;
//            UserDTO value = null;
//            if (result != null) {
//                recordMetadata = result.getRecordMetadata();
//                value = result.getProducerRecord().value();
//            }
//            System.out.println("偏移量 = " + recordMetadata);
//            System.out.println("生产消息 = " + value);
//
//            countDownLatch.countDown();
//        });


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

    }

    @SneakyThrows
    @GetMapping("/test2")
    void test2() {
        UserDTO user = new UserDTO();
        user.setName("jim");
        user.setAge(11);
        user.setBirthday(LocalDateTime.now());
        ListenableFuture<SendResult<String, UserDTO>> sent = kafkaTemplate.send(Topic.USER_JSON, user);

        // 添加回调以处理发送结果
        sent.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, UserDTO> result) {

                System.out.println("发送成功result = " + result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.err.println("发送失败: " + ex.getMessage());
            }
        });


    }


}
