package com.axing;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;

@Component
public class WiselyTenantRoutingDatasource  extends AbstractRoutingDataSource {
    private final WiselyTenantIdResolver wiselyTenantIdResolver;

    public WiselyTenantRoutingDatasource(WiselyTenantIdResolver wiselyTenantIdResolver) {
        this.wiselyTenantIdResolver = wiselyTenantIdResolver;

        setDefaultTargetDataSource(createDatabase("com.mysql.cj.jdbc.Driver","jdbc:mysql://192.168.101.143:3306/public", "root", "123456"));
        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("companya",createDatabase("com.mysql.cj.jdbc.Driver","jdbc:mysql://192.168.101.143:3306/company", "root", "123456"));
        targetDataSources.put("companyb",createDatabase("org.postgresql.Driver","jdbc:postgresql://192.168.101.143:5432/company", "postgres", "123456"));
        setTargetDataSources(targetDataSources);
    }


    @Override
    protected String determineCurrentLookupKey() {
        return wiselyTenantIdResolver.resolveCurrentTenantIdentifier();
    }


    private DataSource createDatabase(String driverClassName,String databaseUrl, String username, String password) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(databaseUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
