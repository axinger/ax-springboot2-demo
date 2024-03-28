package com.github.axinger.service;

import com.github.axinger.event.CustomEvent;
import com.github.axinger.event.EventProducer;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.TimeUnit;

@Service
public class IMyServiceImp implements IMyService {
    private final CustomEventListener customEventListener = new CustomEventListener();
    // 使用AtomicInteger保证线程安全
    private final AtomicInteger count = new AtomicInteger();
    private volatile boolean isListening = false;

    @Autowired
    private EventProducer<CustomEvent> eventProducer;

    @SneakyThrows
    @Override
    public void test(String name) {
        count.set(0);
        try {
            startListening();

            while (count.get() < 3) {
                System.out.println("name = " + name + "count = " + count.get());
                TimeUnit.SECONDS.sleep(2);
            }
        } finally {
            // 确保在方法结束时调用stopListening，避免资源泄露
            stopListening();
        }
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
        public void onApplicationEvent(@NonNull CustomEvent event) {
            // 使用getAndIncrement保证线程安全的递增
            count.set((Integer) event.getSource());
        }
    }
}
