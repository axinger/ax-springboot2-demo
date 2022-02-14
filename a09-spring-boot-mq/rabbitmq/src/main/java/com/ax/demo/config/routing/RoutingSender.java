package com.ax.demo.config.routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by macro on 2020/5/19.
 */
public class RoutingSender {

    private static final String exchangeName = "exchange.direct";
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingSender.class);
    private final String[] keys = {"orange", "black", "green"};
    @Autowired
    private RabbitTemplate template;

    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(exchangeName, key, message);
        LOGGER.info(" [x] Sent '{}'", message);
    }

}
