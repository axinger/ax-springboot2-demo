package com.github.axinger.controller;

import com.axing.sse.ClientManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Tag(name = "SseController", description = "连接sse")
@RestController
public class SseController {

    @Autowired
    private ClientManager clientManager;

    @Operation(summary = "sse页面 ", description = "description注解")
    @RequestMapping(value = "/sse.page")
    public Object ipLogPageInfo1(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sse");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @Operation(summary = "Connect to SSE")
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamEvents(@RequestParam String id) {
        return clientManager.getSink(id).asFlux().doFinally(signalType -> clientManager.removeSink(id));
    }

    @Operation(summary = "发送sse ", description = "description注解")
    @PostMapping("/send")
    public void sendMessageToClient(@RequestParam String clientId, @RequestBody String message) {
        Sinks.Many<String> sink = clientManager.getSink(clientId);
        sink.tryEmitNext(message);
    }

}
