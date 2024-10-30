package com.github.axinger.event;

import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {


    @Override
    public void onApplicationEvent(@NonNull CustomEvent event) {
        System.out.println("收到消息 = " + event);
    }
}
