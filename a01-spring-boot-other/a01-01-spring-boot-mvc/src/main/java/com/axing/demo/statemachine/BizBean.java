package com.axing.demo.statemachine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
@Data
@Slf4j
public class BizBean {

    /**
     * @see States
     */
    private String status = States.DRAFT.name();

    @OnTransition(target = "PUBLISH_TODO")
    public void online() {
        log.info("操做上线，待发布. target status:{}", States.PUBLISH_TODO.name());
        setStatus(States.PUBLISH_TODO.name());
    }

    @OnTransition(target = "PUBLISH_DONE")
    public void publish() {
        log.info("操做发布,发布完成. target status:{}", States.PUBLISH_DONE.name());
        setStatus(States.PUBLISH_DONE.name());
    }

    @OnTransition(target = "DRAFT")
    public void rollback() {
        log.info("操做回滚,回到草稿状态. target status:{}", States.DRAFT.name());
        setStatus(States.DRAFT.name());
    }

}
