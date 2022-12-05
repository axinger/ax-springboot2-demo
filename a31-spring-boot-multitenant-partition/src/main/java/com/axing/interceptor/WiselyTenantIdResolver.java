package com.axing.interceptor;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class WiselyTenantIdResolver implements CurrentTenantIdentifierResolver, // 1
                                                HibernatePropertiesCustomizer  { // 2

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
