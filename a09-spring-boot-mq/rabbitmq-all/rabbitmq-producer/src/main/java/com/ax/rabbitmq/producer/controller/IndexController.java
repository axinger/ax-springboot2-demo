package com.ax.rabbitmq.producer.controller;

import com.ax.rabbitmq.producer.config.DelayQueueConfig;
import com.ax.rabbitmq.producer.config.topic.TopicConfig;
import com.ax.rabbitmq.producer.config.TtlQueueConfig;
import com.ax.rabbitmq.producer.service.IMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName Controller.java
 * @Description TODO
 * @createTime 2022年02月15日 22:19:00
 */
@Controller
public class IndexController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping(value = "/")
    @ResponseBody
    public Object index() {
        Map map = new HashMap<String, Object>(3);
        map.put("success", true);
        map.put("data", "index");
        return map;
    }

    @Autowired
    private IMessageService messageService;


    @GetMapping(value = "/topic/{msg}")
    @ResponseBody
    public Object topic(@PathVariable String msg) {

        // topic 只有msg队列能收到
        Map map = new HashMap<String, Object>(3);
        map.put("type", "topic");
        map.put("msg", "专用 "+msg);
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME, TopicConfig.TOPIC_ROUTING_KEY,map);

        // topic 2个队列都能收到
        Map map2 = new HashMap<String, Object>(3);
        map2.put("type", "topic");
        map2.put("msg", "通用 "+msg);
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME,TopicConfig.TOPIC_GENERAL_ROUTING_KEY,map2);
        return map;
    }


    @GetMapping(value = "/ttl/{msg}")
    @ResponseBody
    public Object ttl(@PathVariable String msg) {

        Map map = new HashMap<String, Object>(3);
        map.put("msg", msg);
        map.put("data", "ttl");
        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE, TtlQueueConfig.QUEUE_A_ROUTING_KEY, "来自10s的消息" + msg);
        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE, TtlQueueConfig.QUEUE_B_ROUTING_KEY, "来自40s的消息" + msg);
        return map;
    }

//    @GetMapping(value = "/ttl/{msg}/{second}")
//    @ResponseBody
//    public Object ttl_time(@PathVariable String msg,@PathVariable String second) {
//
//        Map map = new HashMap<String, Object>(3);
//        map.put("msg", msg);
//        map.put("data", "ttl_time");
//
//        // 死信延迟消息 缺点, 需要使用插件
//        // 第一个消息 延迟时间很长,第二个消息延迟时间很短, 第二个消息不会优先执行
//        rabbitTemplate.convertAndSend(TtlQueueConfig.X_EXCHANGE, TtlQueueConfig.QUEUE_C_ROUTING_KEY, "来自"+second+"的消息" + msg,(message) -> {
//            message.getMessageProperties().setExpiration(second);
//            return message;
//        });
//
//        return map;
//    }

    @GetMapping(value = "/ttl/{msg}/{second}")
    @ResponseBody
    public Object ttl_time(@PathVariable String msg,@PathVariable Integer second) {

        Map map = new HashMap<String, Object>(3);
        map.put("msg", msg);
        map.put("data", "ttl_time使用插件");

        // 使用插件
        // 第一个消息 延迟时间很长,第二个消息延迟时间很短, 第二个消息会优先执行
        rabbitTemplate.convertAndSend(DelayQueueConfig.DELAY_EXCHANGE, DelayQueueConfig.DELAY_ROUTING_KEY, "来自"+second+"的消息" + msg,(message) -> {
            message.getMessageProperties().setDelay(second);
            return message;
        });

        return map;
    }
}
