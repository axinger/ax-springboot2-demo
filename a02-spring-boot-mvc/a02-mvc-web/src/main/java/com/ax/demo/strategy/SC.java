package com.ax.demo.strategy;


import org.springframework.stereotype.Component;

@Component("C")
public class SC implements Strategy {
    @Override
    public String getType(DemoPojo demoPojo) {
        System.out.println("c"+demoPojo);
        return demoPojo.getName();
    }
}
