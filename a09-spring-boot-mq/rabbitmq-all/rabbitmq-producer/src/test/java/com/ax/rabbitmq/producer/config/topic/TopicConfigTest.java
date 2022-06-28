package com.ax.rabbitmq.producer.config.topic;


import com.ax.rabbitmq.producer.RabbitmqProducerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest(classes = RabbitmqProducerApplication.class)
class TopicConfigTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testTopic() {

        String msg = "哈哈";

        // topic 只有msg队列能收到
        Map map = new HashMap<String, Object>(3);
        map.put("type", "topic");
        map.put("msg", "专用 " + msg);
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME, TopicConfig.TOPIC_ROUTING_KEY, map);

        // topic 2个队列都能收到
        Map map2 = new HashMap<String, Object>(3);
        map2.put("type", "topic");
        map2.put("msg", "通用 " + msg);
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME, TopicConfig.TOPIC_GENERAL_ROUTING_KEY, map2);


        // CorrelationData 指定id
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME, TopicConfig.TOPIC_GENERAL_ROUTING_KEY, map2, new CorrelationData(UUID.randomUUID().toString()));
        // 优先级
//        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME,TopicConfig.TOPIC_GENERAL_ROUTING_KEY,map2, message -> {
//            message.getMessageProperties().setPriority(10);
//            return message;
//        });

    }
}
