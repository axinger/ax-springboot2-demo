package com.axing._1simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @Autowired
    RabbitTemplate rabbitTemplate;

    //第一种模式(直连)
    @GetMapping("/01")
    void simpleCustomerTest() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

}
