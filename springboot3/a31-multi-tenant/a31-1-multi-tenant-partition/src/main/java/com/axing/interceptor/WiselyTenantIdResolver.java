package com.axing.interceptor;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * 1、通过实现
 * CurrentTenantIdentifierResolver接口来获取确定TenantId的来源。
 * <p>
 * 1.1、 使用线程本地变量CURRENT_TENANT来存储当前的TenantId；
 * 1.2、通过setCurrentTenant方法接受外部设置当前访问者的TenantId，并存储在线程本地变量CURRENT_TENANT中；
 * 1.3、通过重写接口的
 * resolveCurrentTenantIdentifier方法，获得当前的TenantId；
 * 2、通过重写
 * HibernatePropertiesCustomizer接口的customize方法，可以将当前类注册到Hibernate的配置
 */
@Component
public class WiselyTenantIdResolver implements CurrentTenantIdentifierResolver, // 1
        HibernatePropertiesCustomizer { // 2

    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>(); // 1.1

    public void setCurrentTenant(String currentTenant) { // 1.2
        CURRENT_TENANT.set(currentTenant);
    }

    @Override
    public String resolveCurrentTenantIdentifier() { // 1
        return Optional.ofNullable(CURRENT_TENANT.get()).orElse("unknown");
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) { // 2
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }

}
