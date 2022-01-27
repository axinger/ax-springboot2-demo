package com.ax.demo.strategy2;

import org.springframework.stereotype.Component;

@Component
public class StrategyImpl2 implements Strategy {
    @Override
    public String type() {
        return "2";
    }

    @Override
    public String name() {
        return "我的StrategyImpl2";
    }
}
