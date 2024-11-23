//package com.github.axinger.config;
//
//import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
//import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class CustomBatchConfigurer extends DefaultBatchConfigurer {
//
//    @Autowired
//    private DataSource customDataSource;
//
//    @Override
//    public void setDataSource(DataSource dataSource) {
//
//        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
//        DataSource source = ds.getDataSource("spring_batch");
//
//
//        // 覆盖默认的数据源
//        super.setDataSource(source);
//    }
//}
