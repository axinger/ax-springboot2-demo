package com.github.axinger.config;

import com.github.axinger.domain.OrderPersonEntity;
import com.github.axinger.service.OrderPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyConfiguration {

    @Autowired
    private OrderPersonService orderPersonService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<OrderPersonEntity> list = orderPersonService.list();
        System.out.println("list = " + list);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void run2() {
        List<OrderPersonEntity> list = orderPersonService.list();
        System.out.println("list2 = " + list);
    }
}
