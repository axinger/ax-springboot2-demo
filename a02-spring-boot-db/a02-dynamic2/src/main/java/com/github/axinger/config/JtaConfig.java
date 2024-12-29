//package com.github.axinger.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class JtaConfig {
//
//    @Configuration
//    @MapperScan(basePackages = MasterDataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
//    public static class MasterDataSourceConfiguration {
//        static final String PACKAGE = "com.github.axinger.db.master.mapper";
//
//        @Bean(name = "masterDataSource")
//        @ConfigurationProperties(prefix = "spring.datasource.master")
//        @Primary
//        public DataSource masterDataSource() {
//            return new AtomikosDataSourceBean();
//        }
//
//        @Bean(name = "masterSqlSessionFactory")
//        @Primary
//        public SqlSessionFactory masterSqlSessionFactory() throws Exception {
//            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//            sessionFactory.setDataSource(masterDataSource());
//            return sessionFactory.getObject();
//        }
//    }
//
//    @Configuration
//    @MapperScan(basePackages = SlaveDataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = "slaveSqlSessionFactory")
//    public static class SlaveDataSourceConfiguration {
//        static final String PACKAGE = "com.github.axinger.db.slave.mapperssssss";
//
//        @Bean(name = "slaveDataSource")
//        @ConfigurationProperties(prefix = "spring.datasource.slave")
//        public DataSource slaveDataSource() {
//            return new AtomikosDataSourceBean();
//        }
//
//        @Bean(name = "slaveSqlSessionFactory")
//        public SqlSessionFactory slaveSqlSessionFactory() throws Exception {
//            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//            sessionFactory.setDataSource(slaveDataSource());
//            return sessionFactory.getObject();
//        }
//    }
//
//}
