package com.ax.controller;

import com.ax.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @Author JCccc
 * @Description
 * @Date 2021/8/20 8:53
 */
@Controller
public class TestController {
    @Autowired
    public SimpMessagingTemplate template;

    /**
     * 广播
     *
     * @param msg
     */
    @ResponseBody
    @RequestMapping("/pushToAll")
    public void subscribe(@RequestBody Message msg) {
        // 点对多
        template.convertAndSend("/topic/all", msg.getContent());
    }

    /**
     * 点对点
     */
//    @ResponseBody
//    @RequestMapping("/pushToOne")
//    @MessageMapping("/alone")
//    public void queue(@RequestBody Message msg) {
//        System.out.println("单点发送消息");
//        /*使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
//        "/user/" + 用户Id + "/message",其中"/user"是固定的*/
//        /// 也许看到这里，你会觉得很奇怪，为什么我们推的主题是 /message，但是前端订阅的却是
//        //    "/user/" + 用户Id + "/message"
//
//        template.convertAndSendToUser(msg.getTo(), "/message", msg.getContent());
//
//    }
    @ResponseBody
    @RequestMapping("/pushToOne")
    @MessageMapping("/alone")
    public void queue(Principal principal, @RequestBody Message msg) {
        System.out.println("单点发送消息 principal = " + principal);
        System.out.println("单点发送消息 Message = " + msg);

        /*使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
        "/user/" + 用户Id + "/message",其中"/user"是固定的*/
        /// 也许看到这里，你会觉得很奇怪，为什么我们推的主题是 /message，但是前端订阅的却是
        //    "/user/" + 用户Id + "/message"

        template.convertAndSendToUser(msg.getTo(), "/message", msg.getContent());

    }


}
