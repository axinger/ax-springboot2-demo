package com.github.axinger.controller;

import com.github.axinger.config.MqttProviderService;
import com.github.axinger.config.Topic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private MqttProviderService mqttProviderService;

    @GetMapping(value = "/1")
    public String test() {

        mqttProviderService.push(Topic.OPC_TOPIC, 1, "test");

        return "ok";
    }
}
