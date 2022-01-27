package com.ax.demo.strategy;

import org.springframework.stereotype.Component;

@Component("A")
public class SA implements Strategy {
    @Override
    public String getType(DemoPojo demoPojo) {
        System.out.println("A,getVpcList ==========="+demoPojo);
        return demoPojo.getName();
    }
}
