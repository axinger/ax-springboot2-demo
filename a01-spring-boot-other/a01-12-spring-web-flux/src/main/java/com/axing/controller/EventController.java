package com.axing.controller;

import com.axing.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@Tag(name = "EventController", description = "发送sse")
public class EventController {

    @Autowired
    private EventService eventService;

    @Operation(summary = "发送sse ", description = "description注解")
    @PostMapping("/send")
    public void sendEvent(String data) {
        // 在这里处理数据

        // 发布事件
        eventService.publishEvent(data);
    }
}
