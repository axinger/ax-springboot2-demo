package com.axing.common.quartz.config;

import com.axing.common.quartz.service.QuartzService;
import com.axing.common.quartz.service.impl.QuartzServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzAutoConfig {

    @Bean
    @ConditionalOnMissingBean(QuartzService.class)
    public QuartzService quartzService() {
        return new QuartzServiceImpl();
    }
}
