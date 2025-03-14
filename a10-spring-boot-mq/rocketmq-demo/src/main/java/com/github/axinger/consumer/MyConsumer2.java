package com.github.axinger.consumer;

import cn.hutool.core.util.RandomUtil;
import com.github.axinger.config.Topic;
import com.github.axinger.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyConsumer2 {


    @Component
    @RocketMQMessageListener(
            consumerGroup = Topic.GROUP_2,
            topic = Topic.TOPIC_1,
            consumeThreadNumber = 4
    )
    public static class Consumer2 implements RocketMQListener<User> {
        // 监听到消息就会执行此方法

        /// 这里只要不抛出异常，就会认为是成功返回CONSUME_SUCCESS，否则返回RECONSUME_LATER，boker会重试，
        /// 从RocketMQ Spring 2.2.0开始，RocketMQ Srping支持Pull模式消费
        @SneakyThrows
        @Override
        public void onMessage(User user) {
            int randomInt = RandomUtil.randomInt(1, 20);
            log.info("GROUP_2:当前线程池={},监听到消息：user={},randomInt={}", Thread.currentThread().getName(), user.getId(), randomInt);
            TimeUnit.SECONDS.sleep(randomInt);
//        log.info("当前线程池={},消息结束", Thread.currentThread().getName());
        }


    }
}
