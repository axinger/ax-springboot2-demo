package com.axing.demo.event;

import org.springframework.context.ApplicationEvent;

public class MyCustomEvent extends ApplicationEvent {

    public MyCustomEvent(Object source) {
        super(source);
    }
}
