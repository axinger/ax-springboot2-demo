package com.ax.rabbitmq.producer.config.fanout;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FanoutExchangeConfigTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     * 发布订阅模式,所有queue都会收到消息
     * */


    @Test
    public void test1() {

        // 第二个参数是key,不是队列名称
        rabbitTemplate.convertAndSend(FanoutExchangeConfig.EXCHANGE_NAME, FanoutExchangeConfig.QUEUE_NAME_A, "发布订阅模式,给A队列");
    }

    @Test
    public void test2() {

        rabbitTemplate.convertAndSend(FanoutExchangeConfig.EXCHANGE_NAME, FanoutExchangeConfig.QUEUE_NAME_B, "发布订阅模式,给B队列");
    }

    @Test
    public void test3() {

        rabbitTemplate.convertAndSend(FanoutExchangeConfig.EXCHANGE_NAME, null, "发布订阅模式,给AB队列");
    }
}
