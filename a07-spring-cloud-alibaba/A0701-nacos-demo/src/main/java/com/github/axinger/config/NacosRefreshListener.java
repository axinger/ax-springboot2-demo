package com.github.axinger.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NacosRefreshListener {



    @EventListener
    public void onRefresh(RefreshScopeRefreshedEvent event) {
        log.info("RefreshScope刷新事件: {}", event.getSource());
    }

    /**
     * 监听EnvironmentChangeEvent也可以
     */
    @EventListener
    public void onEnvironmentChange(org.springframework.cloud.context.environment.EnvironmentChangeEvent event) {
        log.info("EnvironmentChangeEvent: {},{}}", event.getKeys(), event.getSource());

    }
}
