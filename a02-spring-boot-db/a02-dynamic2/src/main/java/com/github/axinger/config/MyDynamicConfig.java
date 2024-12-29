package com.github.axinger.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class MyDynamicConfig {


    @Autowired
    private DataSourceProperties dataSourceProperties;

    protected DataSource createDataSource(String key) {
        DataSourceProperties.DataSourceBean bean = dataSourceProperties.getDatasource().get(key);
        if (bean == null) {
            throw new IllegalArgumentException("No configuration found for data source: " + key);
        }
        return DataSourceBuilder.create().type(HikariDataSource.class).url(bean.getUrl()).username(bean.getUsername()).password(bean.getPassword()).driverClassName(bean.getDriverClassName()).build();
    }

    @Configuration
    @EnableConfigurationProperties
    @MapperScan(basePackages = "com.github.axinger.db.master.mapper", sqlSessionFactoryRef = "masterSqlSessionFactory")
    public static class MasterDataSourceConfig extends MyDynamicConfig {
//        @Primary
//        @Bean(name = "masterDataSource")
//        @Qualifier("masterDataSource")
//        @ConfigurationProperties(prefix = "spring.datasource.master")
//        public DataSource masterDataSource() {
//            return this.createDataSource("master");
////            return DataSourceBuilder.create()
////                    .type(HikariDataSource.class)
////                    .build();
//        }

        @Bean(name = "masterDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.master")
        @Primary
        public DataSource masterDataSource() {
            return new AtomikosDataSourceBean();
        }

        @Bean(name = "masterSqlSessionFactory")
        public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
            MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
            sessionFactory.setDataSource(masterDataSource);
//            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/master/*.xml"));


            List<Resource> mapperLocations = Collections.singletonList(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/master/*.xml")[0]);

            sessionFactory.setMapperLocations(mapperLocations.toArray(new Resource[0]));
            return sessionFactory.getObject();
        }

        @Bean(name = "masterSqlSessionTemplate")
        public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean(name = "masterTransactionManager")
        @Primary
        public PlatformTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) {
            return new DataSourceTransactionManager(masterDataSource);
        }

    }

    @Configuration
    @EnableConfigurationProperties
    @MapperScan(basePackages = "com.github.axinger.db.slave.mapper", sqlSessionFactoryRef = "slaveSqlSessionFactory")
    public static class SlaveDataSourceConfig extends MyDynamicConfig {

//        @Bean(name = "slaveDataSource")
//        @ConfigurationProperties(prefix = "spring.datasource.slave")
//        public DataSource slaveDataSource() {
//            return this.createDataSource("slave");
////            return DataSourceBuilder.create()
////                    .type(HikariDataSource.class)
////                    .build();
//        }

        @Bean(name = "slaveDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.slave")
        public DataSource slaveDataSource() {
            return new AtomikosDataSourceBean();
        }

        @Bean(name = "slaveSqlSessionFactory")
        public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource slaveDataSource) throws Exception {
            MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
            sessionFactory.setDataSource(slaveDataSource);
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/slave/*.xml"));
            return sessionFactory.getObject();
        }


        @Bean(name = "slaveSqlSessionTemplate")
        public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean(name = "slaveTransactionManager")
        public PlatformTransactionManager slaveTransactionManager(@Qualifier("slaveDataSource") DataSource slaveDataSource) {
            return new DataSourceTransactionManager(slaveDataSource);
        }
    }


}

