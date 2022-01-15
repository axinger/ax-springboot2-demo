package com.ax.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by macro on 2020/5/19.
 */
public class TopicSender {

    private static final String exchangeName = "exchange.topic";
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicSender.class);
    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};
    @Autowired
    private RabbitTemplate template;

    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index % keys.length;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(exchangeName, key, message);
        LOGGER.info(" [x] Sent '{}'", message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}