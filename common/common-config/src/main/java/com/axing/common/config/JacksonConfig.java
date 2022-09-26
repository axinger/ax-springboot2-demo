package com.axing.common.config;

import com.axing.common.model.JacksonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName FastJsonConfig.java
 * @description https://github.com/alibaba/fastjson2/blob/main/docs/features_cn.md
 * @createTime 2022年09月21日 20:01:00
 */
@Configuration
public class JacksonConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters(new JacksonHttpMessageConverter());
    }

}