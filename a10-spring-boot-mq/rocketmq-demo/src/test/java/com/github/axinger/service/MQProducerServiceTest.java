package com.github.axinger.service;

import com.github.axinger.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MQProducerServiceTest {

    @Autowired
    private MQProducerService mqProducerService;

    @Test
    void send() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.send(user);
    }

    @Test
    void sendMsg() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.sendMsg(user);
    }

    @Test
    void sendAsyncMsg() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.sendAsyncMsg(user);
    }

    @Test
    void sendDelayMsg() {
        User user = new User();
        user.setName("延迟消息");
        user.setAge(10);
        mqProducerService.sendDelayMsg(user, 5);
    }

    @Test
    void sendOneWayMsg() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.sendOneWayMsg(user);
    }

    @Test
    void sendTagMsg() {
        User user = new User();
        user.setName("jim");
        user.setAge(10);
        mqProducerService.sendTagMsg(user);
    }
}
