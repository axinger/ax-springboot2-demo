package com.axing.demo.controller;

import com.axing.demo.config.MyWebSocket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/all")
    public void all() {

        MyWebSocket.sendAllMessage("群发消息");
    }
}
