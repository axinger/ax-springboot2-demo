package com.axing.demo.statemachine;

import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.statemachine.region.Region;
import reactor.core.publisher.Mono;

@Data
public class EventResult implements StateMachineEventResult<States,Events> {

    private final Region<States, Events> region;
    private final Message<Events> message;
    private final ResultType resultType;
    private final Mono<? extends Object> complete;

    public EventResult(Region<States, Events> region, Message<Events> message, ResultType resultType,
                       Mono<Void> complete) {
        this.region = region;
        this.message = message;
        this.resultType = resultType;
        this.complete = complete != null ? complete : Mono.empty();
    }

    @Override
    public Region<States, Events> getRegion() {
        return null;
    }

    @Override
    public Message<Events> getMessage() {
        return null;
    }

    @Override
    public ResultType getResultType() {
        return null;
    }

    @Override
    public Mono<Void> complete() {
        return null;
    }
}
