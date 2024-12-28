package com.github.axinger.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.github.axinger.db.master.mapper", sqlSessionFactoryRef = "masterSqlSessionFactory")
@MapperScan(basePackages = "com.github.axinger.db.slave.mapper", sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class Config {


    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(DataSource dataSource) throws Exception {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource datasource = ds.getDataSource("master");
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/master/*.xml"));
        return sessionFactory.getObject();
    }

//    @Bean(name = "masterSqlSessionTemplate")
//    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }


    @Bean(name = "slaveSqlSessionFactory")
    @DS("slave")
    public SqlSessionFactory slaveSqlSessionFactory(DataSource dataSource) throws Exception {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource datasource = ds.getDataSource("slave");
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/slave/*.xml"));
        return sessionFactory.getObject();
    }


//    @Bean(name = "slaveSqlSessionTemplate")
//    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}
