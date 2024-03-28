package com.github.axinger.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventProducer<E extends ApplicationEvent> {

    private final List<ApplicationListener<E>> customEventListeners = new ArrayList<>();

    public void produceEvent(E o) {
        // 通知所有注册的监听器
        for (ApplicationListener<E> listener : customEventListeners) {
            listener.onApplicationEvent(o);
        }
    }

    public void addCustomEventListener(ApplicationListener<E> listener) {
        customEventListeners.add(listener);
    }

    public void removeCustomEventListener(ApplicationListener<E> listener) {
        customEventListeners.remove(listener);
    }
}
