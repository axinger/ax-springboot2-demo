//package com.github.axinger.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Configuration
//public class DuckDBConfig {
//
//    @Bean
//    @Primary
//    public DataSource dataSource() throws SQLException {
//        // 使用内存数据库
//        return new org.duckdb.DuckDBDataSource("jdbc:duckdb:memory:");
//
//        // 或者使用文件数据库
//        // return new org.duckdb.DuckDBDataSource("jdbc:duckdb:my_database.db");
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//}
