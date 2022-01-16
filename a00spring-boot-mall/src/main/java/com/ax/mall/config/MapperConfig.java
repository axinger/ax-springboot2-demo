package com.ax.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName MapperConfig.java
 * @Description TODO
 * @createTime 2022年01月17日 00:59:00
 */

@MapperScan("com.ax.mall.mapper")
@Configuration
public class MapperConfig {

}
