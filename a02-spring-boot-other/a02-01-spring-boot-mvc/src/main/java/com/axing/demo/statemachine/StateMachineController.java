package com.axing.demo.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StateMachineController {

    @Autowired
    private StateMachine<States, Events> stateMachine;


    @PostMapping("event")

    public String event(@RequestParam(name = "event") Events event) {

        Message<Events> message = MessageBuilder.withPayload(event).build();

        Events payload = stateMachine.sendEvent(Mono.just(message)).blockLast().getMessage().getPayload();
        return payload.name();

    }

    @GetMapping("/state")
    public Mono<States> state() {
        return Mono.defer(() -> Mono.justOrEmpty(stateMachine.getState().getId()));
    }


    @PostMapping("/events")
    public Flux<StateMachineEventResult<States, Events>> events(@RequestBody Flux<EventData> eventData) {
        return eventData
                .filter(ed -> ed.getEvent() != null)
                .map(ed -> MessageBuilder.withPayload(ed.getEvent()).build())
                .flatMap(m -> stateMachine.sendEvent(Mono.just(m)))
                .map(val -> StateMachineEventResult.from(val.getRegion(), val.getMessage(), val.getResultType()));
    }
}
