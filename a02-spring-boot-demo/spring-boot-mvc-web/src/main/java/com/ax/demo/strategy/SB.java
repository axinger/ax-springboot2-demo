package com.ax.demo.strategy;

import org.springframework.stereotype.Component;

@Component("B")
public class SB implements Strategy {
    @Override
    public String getType(DemoPojo demoPojo) {
        System.out.println("b,getVpcList ===========" + demoPojo);
        return demoPojo.getName();
    }
}
