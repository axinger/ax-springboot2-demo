package com.github.axinger.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.github.axinger.producer.PulsarProducer;
import com.github.axinger.topic.Topic;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/pulsar")
public class PulsarController {

    @Autowired
    private PulsarProducer pulsarProducer;

    @Autowired
    private PulsarTemplate<Map> template;


    @GetMapping(value = "/sendMessage")
    public void sendMessage() {
        Map<Object, Object> map = new HashMap<>();
        map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
        map.put("data", "发送普通消息");
        pulsarProducer.send(map);
    }


    @GetMapping(value = "/deliverAfter")
    @ResponseBody
    public void deliverAfter() {
        // Map map = new HashMap<>();
        // map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
        // map.put("data", "发送普通消息");
        // pulsarProducer.send(map);
        pulsarProducer.deliverAfter();

    }


    @GetMapping("/more")
    public void more() throws PulsarClientException {
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "发送普通消息");
            template.send(Topic.SHARED_TOPIC, map);
        }
    }
}
