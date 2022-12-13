package com.axing.demo;

import com.axing.demo.api.Topic;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class KafkaProducerApplicationTests {


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @SneakyThrows
    @Test
    void contextLoads() {

        Map<String, Object> map = new HashMap<>();
        map.put("data", LocalDateTime.now());
        map.put("topic", Topic.SIMPLE);



        CompletableFuture future = kafkaTemplate.send(Topic.SIMPLE,map);

        // future.w(new ListenableFutureCallback<SendResult<String, Object>>() {
        //     @Override
        //     public void onFailure(Throwable throwable) {
        //         log.info("发送消息失败,{}" + throwable.getMessage());
        //     }
        //
        //     @Override
        //     public void onSuccess(SendResult<String, Object> sendResult) {
        //         log.info("发送消息成功,{}", sendResult.toString());
        //     }
        // });

        future.whenComplete((res, error) ->{

            System.out.println("res = " + res);
            System.out.println("error = " + error);

        });

        TimeUnit.MINUTES.sleep(5);
    }

}
