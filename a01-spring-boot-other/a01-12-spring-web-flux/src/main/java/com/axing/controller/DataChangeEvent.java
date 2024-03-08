package com.axing.controller;

import org.springframework.context.ApplicationEvent;

public class DataChangeEvent extends ApplicationEvent {

    public DataChangeEvent(Object source) {
        super(source);
    }
}
