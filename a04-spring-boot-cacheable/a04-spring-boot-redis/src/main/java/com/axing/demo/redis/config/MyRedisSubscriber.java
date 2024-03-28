package com.axing.demo.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class MyRedisSubscriber {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisMessageListenerContainer redisContainer;

    @Autowired
    public MyRedisSubscriber(RedisTemplate<String, String> redisTemplate, RedisMessageListenerContainer redisContainer) {
        this.redisTemplate = redisTemplate;
        this.redisContainer = redisContainer;
    }

    public void testSubscribe() {
        // 创建消息监听器
        MessageListener listener = new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                String channel = new String(message.getChannel());
                String body = new String(message.getBody());
                System.out.println("Received message: " + body + " from channel: " + channel);
            }
        };

        // 添加消息监听器到容器中
        redisContainer.addMessageListener(listener, new ChannelTopic("your-channel-name")); // 替换成你的频道名

        // 发布一条测试消息
        redisTemplate.convertAndSend("your-channel-name", "Test message");

        // 在这里可以做其他操作...

        // 停止消息订阅，可选
        redisContainer.removeMessageListener(listener);
    }
}
