package com.github.axinger.consumer;

import com.github.axinger.config.Topic;
import com.github.axinger.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RocketMQMessageListener(
        consumerGroup = Topic.GROUP_1,
        topic = Topic.TOPIC_1
)
public class Consumer2 implements RocketMQListener<User> {
    // 监听到消息就会执行此方法
    @SneakyThrows
    @Override
    public void onMessage(User user) {
        log.info("当前线程池={},监听到消息：user={}", Thread.currentThread().getName(), user);
        TimeUnit.SECONDS.sleep(10);
        log.info("当前线程池={},消息结束", Thread.currentThread().getName());
    }

}
