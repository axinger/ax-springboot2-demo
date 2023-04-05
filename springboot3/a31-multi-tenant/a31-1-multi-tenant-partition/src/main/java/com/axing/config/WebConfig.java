package com.axing.config;

import com.axing.interceptor.TenantIdInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通过定义一个Spring MVC的拦截器，在每个request请求的头部设置key：x-tenant-id
 * （如：companya），在拦截器中获取到TenantId后设置WiselyTenantIdResolver的TenantId，即当前的TenantId。
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final TenantIdInterceptor tenantIdInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantIdInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
