package com.axing.demo.controller;

import com.axing.demo.model.User;
import com.axing.demo.service.MQProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rocketmq")
public class RocketMQController {

    @Autowired
    private MQProducerService mqProducerService;

    @GetMapping("/send")
    void send() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.send(user);
    }

    @GetMapping("/sendMsg")
    void sendMsg() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.sendMsg(user);
    }

    @GetMapping("/sendAsyncMsg")
    void sendAsyncMsg() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.sendAsyncMsg(user);
    }

    @GetMapping("/sendDelayMsg")
    void sendDelayMsg() {
        User user = new User();
        user.setName("延迟消息");
        user.setAge(10);
        mqProducerService.sendDelayMsg(user, 2);
    }

    @GetMapping("/sendOneWayMsg")
    void sendOneWayMsg() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.sendOneWayMsg(user);
    }

    @GetMapping("/sendTagMsg")
    void sendTagMsg() {
        User user = new User();
        user.setName("带有tag的字符消息");
        user.setAge(10);
        mqProducerService.sendTagMsg(user);
    }
}
