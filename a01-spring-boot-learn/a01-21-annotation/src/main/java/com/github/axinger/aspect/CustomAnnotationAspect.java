package com.github.axinger.aspect;

import com.github.axinger.annotation.MyCustomAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MapPropertySource;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.util.PropertyPlaceholderHelper;

@Aspect
@Component
@Slf4j
public class CustomAnnotationAspect {

    private final PropertyPlaceholderResolver placeholderResolver;

    @Autowired
    public CustomAnnotationAspect(PropertyPlaceholderResolver placeholderResolver) {
        this.placeholderResolver = placeholderResolver;
    }

    @Before("@annotation(myCustomAnnotation)")
    public void beforeMethod(MyCustomAnnotation myCustomAnnotation) {
        // 获取注解中的值
        String valueStr = myCustomAnnotation.value();
        String value = placeholderResolver.resolvePlaceholder(valueStr);
        // 解析占位符
        String numStr = placeholderResolver.resolvePlaceholder(myCustomAnnotation.num());
        int num = Integer.parseInt(numStr);
        log.info("获取注解参数={},{}", value, num);
    }
}
