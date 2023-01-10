package com.axing.db.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.axing.db.mapper")
public class DBConfig {
}
