package com.axing.common.config;

import com.axing.common.model.JacksonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonAutoConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String dateFormat;


    @Bean
    public HttpMessageConverters httpMessageConverters() {
        return new HttpMessageConverters(new JacksonHttpMessageConverter(dateFormat));
    }

//    @Bean
//    public HttpMessageConverters httpMessageConverters() {
//        return new HttpMessageConverters(new FastJson2Converter());
//    }
}
