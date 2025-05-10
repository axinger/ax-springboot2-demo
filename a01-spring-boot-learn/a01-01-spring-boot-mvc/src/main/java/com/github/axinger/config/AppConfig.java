package com.github.axinger.config;

import com.github.axinger.model.bean.AppProperties;
import com.github.axinger.model.bean.MyBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {

//    @ConditionalOnProperty(name = "my.feature.enabled", havingValue = "true")
//    @ConditionalOnProperty(prefix = "my.app.cache", name = "preload", havingValue = "true")
//    @ConditionalOnExpression("${my.module.enabled:false} && ${my.module.mode} == 'advanced'")


    @Bean("myBean1")
    @Conditional(MyCondition.class)
    public MyBean myBean1(AppProperties appProperties) {
        return new MyBean(appProperties);
    }

    @Bean("myBean2")
    @Conditional(MyCondition2.class)
    public MyBean myBean2(AppProperties appProperties) {
        return new MyBean(appProperties);
    }
}
