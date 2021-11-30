package com.ax.demo.listener;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MySpringApplicationRunListener implements SpringApplicationRunListener {


    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("MySpringApplicationRunListener.contextLoaded");
    }
}
