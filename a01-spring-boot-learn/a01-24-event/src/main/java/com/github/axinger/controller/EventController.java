package com.github.axinger.controller;

import com.github.axinger.event.CustomEvent;
import com.github.axinger.event.EventProducer;
import com.github.axinger.service.IMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Validated
public class EventController {

    @Autowired
    private IMyService iMyService;
    @Autowired
    private EventProducer<CustomEvent> eventProducer;

    @GetMapping("/test")
    public Object login(@NotEmpty(message = "name不能为空") String name) {
        CompletableFuture.runAsync(() -> {
            iMyService.test(name);
        });
        return List.of(name);
    }

    @GetMapping("/send")
    public Object send(int count) {
//        applicationEventPublisher.publishEvent(new CustomEvent(count));
        eventProducer.produceEvent(new CustomEvent(count));
        return List.of(count);
    }
}
