package com.axing;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.axing.topic.Topic;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.MessageId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class PulsarProducerApplicationTest {

    @Autowired
    private PulsarTemplate template;

    @Test
    void test1() {
        try {
            Map map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "独占模式(Exclusive)");
            map.put("topic", Topic.EXCLUSIVE_TOPIC);

            MessageId messageId = template.send(Topic.EXCLUSIVE_TOPIC, map);
            System.out.println("messageId = " + messageId);
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test2() {
        try {
            Map map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "灾备模式(Failover)");
            map.put("topic", Topic.FAILOVER_TOPIC);

            //异步发送
            //messageId = 1873:0:-1:0
            template.sendAsync(Topic.FAILOVER_TOPIC, map).whenComplete((messageId, err) -> {
                System.out.println("messageId = " + messageId.toString());
            });

            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test3() {
        try {
            Map map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "共享订阅(Shared)");
            map.put("topic", Topic.SHARED_TOPIC);

            //异步发送
            //messageId = 1873:0:-1:0
            template.sendAsync(Topic.SHARED_TOPIC, map).whenComplete((messageId, err) -> {
                System.out.println("messageId = " + messageId.toString());
            });
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test4() {
        try {
            Map map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "Key_Shared");
            map.put("topic", Topic.KEY_SHARED_TOPIC);

            //异步发送
            //messageId = 1873:0:-1:0
            MessageId messageId = template.createMessage(Topic.KEY_SHARED_TOPIC, map).key("1")
                    .send();
            System.out.println("messageId = " + messageId);
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test5() {
        try {
            Map map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "发送延迟消息");

            MessageId messageId = template.createMessage(Topic.DELIVER_AFTER_TOPIC, map)
                    .deliverAfter(3, TimeUnit.SECONDS)
                    .send();
            System.out.println("messageId = " + messageId);
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void test6() {
        try {
            Map map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "发送定时消息");

            long milli = LocalDateTimeUtil.toEpochMilli(LocalDateTime.now().plusSeconds(3));

            MessageId messageId = template.createMessage(Topic.DELIVER_AFTER_TOPIC, map)
                    .deliverAt(milli)
                    .send();
            System.out.println("messageId = " + messageId);
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}