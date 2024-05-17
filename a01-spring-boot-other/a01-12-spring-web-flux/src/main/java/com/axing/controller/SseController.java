package com.axing.controller;

import com.axing.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Tag(name = "SseController", description = "连接sse")
@RestController
public class SseController {

    @Operation(summary = "sse页面 ", description = "description注解")
    @RequestMapping(value = "/sse.page")
    public Object ipLogPageInfo1() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sse");
        return modelAndView;
    }

    @Autowired
    private Sinks.Many<String> sseSink;

    @Operation(summary = "链接sse ")
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents() {
        return sseSink.asFlux();
    }


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
