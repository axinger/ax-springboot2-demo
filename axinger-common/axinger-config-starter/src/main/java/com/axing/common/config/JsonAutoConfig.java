package com.axing.common.config;

import com.axing.common.model.JacksonConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JacksonProperties.class)
@Slf4j
public class JsonAutoConfig {


    @Bean
    public HttpMessageConverters httpMessageConverters(JacksonProperties jacksonProperties) {
        return new HttpMessageConverters(new JacksonConverter(jacksonProperties));
    }

//    @Bean
//    public HttpMessageConverters httpMessageConverters() {
//        return new HttpMessageConverters(new FastJson2Converter());
//    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("全局objectMapper = " + objectMapper);
        return objectMapper;
    }
}
