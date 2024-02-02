package com.axing.demo.controller;

import com.axing.demo.config.Topic;
import com.axing.demo.model.MessageWrapper;
import com.axing.demo.model.User;
import com.axing.demo.service.MQProducerService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping("/rocketmq")
public class RocketMQController {

    @Autowired
    private MQProducerService mqProducerService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

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

    @GetMapping("/noOrder")
    public Boolean updateUser() {

        String userTopic = Topic.sync_user_topic;

        IntStream.range(0, 10).forEach(i -> {

            MessageWrapper messageSend = MessageWrapper.builder()
                    .keys(userTopic).message("用户向钱包中转入100元，短信通知用户目前剩余金额100元：" + i)
                    .timestamp(System.currentTimeMillis()).build();

            MessageWrapper messageSend1 = MessageWrapper.builder()
                    .keys(userTopic).message("用户下单商品消费50元，短信通知用户目前剩余金额50元：" + i)
                    .timestamp(System.currentTimeMillis()).build();

            rocketMQTemplate.syncSend(userTopic, messageSend);

            rocketMQTemplate.syncSend(userTopic, messageSend1);
        });


        return Boolean.TRUE;
    }
}
