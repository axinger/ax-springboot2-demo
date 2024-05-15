package com.axing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Tag(name = "SseController", description = "连接sse")
@RestController
public class SseController {

    @RequestMapping(value = "/sse.page")
    @Operation(summary = "sse页面 ", description = "description注解")
    public Object ipLogPageInfo1() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sse");
        return modelAndView;
    }

    @Autowired
    private Sinks.Many<String> sseSink;

    @Operation(summary = "链接sse ", description = "description注解")
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents() {
        return sseSink.asFlux();
    }
}
