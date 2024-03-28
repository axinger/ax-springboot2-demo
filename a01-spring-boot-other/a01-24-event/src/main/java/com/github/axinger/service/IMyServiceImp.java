package com.github.axinger.service;

import com.github.axinger.event.CustomEvent;
import com.github.axinger.event.EventProducer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class IMyServiceImp implements IMyService {

    private final CustomEventListener customEventListener = new CustomEventListener();
    int count;
    @Autowired
    private EventProducer<CustomEvent> eventProducer;
    private boolean isListening = false;

    @SneakyThrows
    @Override
    public void test(String name) {
        // 开始监听事件
        startListening();

        // 执行一些代码
        while (count < 3) {
            System.out.println("name = " + name + "count = " + count);
            TimeUnit.SECONDS.sleep(2);
        }

        // 结束监听事件
        stopListening();
    }

    private void startListening() {
        if (!isListening) {
            eventProducer.addCustomEventListener(customEventListener);
            isListening = true;
            System.out.println("Listening for events...");
        }
    }

    private void stopListening() {
        if (isListening) {
            eventProducer.removeCustomEventListener(customEventListener);
            isListening = false;
            System.out.println("Stopped listening for events.");
        }
    }

    // 内部类，用于处理自定义事件
    private class CustomEventListener implements ApplicationListener<CustomEvent> {
        @Override
        public void onApplicationEvent(CustomEvent event) {
            System.out.println("event = " + event);
            count = (Integer) event.getSource();
        }
    }
}
