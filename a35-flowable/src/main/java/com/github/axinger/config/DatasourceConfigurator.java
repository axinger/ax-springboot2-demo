package com.github.axinger.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.impl.AbstractEngineConfiguration;
import org.flowable.common.engine.impl.EngineConfigurator;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicBoolean;

// 必须配置这个文件 org.flowable.common.engine.impl.EngineConfigurator
@Slf4j
@Configuration
public class DatasourceConfigurator implements EngineConfigurator {


    private static final AtomicBoolean initialized = new AtomicBoolean();

//    @Override
//    public void beforeInit(AbstractEngineConfiguration engineConfiguration) {
//
//
//        if (initialized.compareAndSet(false, true)) {
//            log.info("启动中动态切换数据源workflow");
//            DynamicDataSourceContextHolder.push("db_flowable");
//        }
//    }

    @Override
    public void beforeInit(AbstractEngineConfiguration abstractEngineConfiguration) {
        if (initialized.compareAndSet(false, true)) {
            DataSource dataSource = abstractEngineConfiguration.getDataSource();
            if (dataSource instanceof TransactionAwareDataSourceProxy) {
                dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
            }
            if (dataSource instanceof DynamicRoutingDataSource) {
                DataSource ds = ((DynamicRoutingDataSource) dataSource).getDataSource("db_flowable");
                abstractEngineConfiguration.setDataSource(ds);
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
