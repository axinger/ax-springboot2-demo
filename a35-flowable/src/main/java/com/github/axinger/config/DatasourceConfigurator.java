package com.github.axinger.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.EngineConfigurator;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Configuration
public class DatasourceConfigurator implements EngineConfigurator {


    private static final AtomicBoolean initialized = new AtomicBoolean();


    @Override
    public void beforeInit(AbstractEngineConfiguration abstractEngineConfiguration) {
        if (initialized.compareAndSet(false, true)) {
            DataSource dataSource = abstractEngineConfiguration.getDataSource();
            if (dataSource instanceof TransactionAwareDataSourceProxy proxy) {
                dataSource = proxy.getTargetDataSource();
            }
            if (dataSource instanceof DynamicRoutingDataSource dynamicRoutingDataSource) {
                abstractEngineConfiguration.setDataSource(dynamicRoutingDataSource.getDataSource("db_flowable"));
            }

        }
    }

    @Override
    public void configure(AbstractEngineConfiguration engineConfiguration) {
    }

    @Override
    public int getPriority() {
        return 0; // 保证该优先级最高
    }

}
