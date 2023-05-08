package com.axing.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.axing.producer.PulsarProducer;
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
}
