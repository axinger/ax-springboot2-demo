//package com.ax.demo.config;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson2.JSONReader;
//import com.alibaba.fastjson2.JSONWriter;
//import com.alibaba.fastjson2.support.config.FastJsonConfig;
//import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * @author xing
// * @version 1.0.0
// * @ClassName FastJsonConfig.java
// * @description https://github.com/alibaba/fastjson2/blob/main/docs/features_cn.md
// * @createTime 2022年05月28日 20:01:00
// */
//@Configuration
//public class JsonConfig {
//
//    @Bean
//    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
//
//        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
//        messageConverter.setFastJsonConfig(fastJsonConfig());
//        messageConverter.setDefaultCharset(StandardCharsets.UTF_8);
//        messageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        return messageConverter;
//
//    }
//
//    @Bean
//    public FastJsonConfig fastJsonConfig(){
//        FastJsonConfig config = new FastJsonConfig();
//        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
//        config.setDateFormat("yyyy-MM-dd日 HH:mm:ss");
//        config.setWriterFeatures(
//                JSONWriter.Feature.NullAsDefaultValue
//        );
//        return config;
//    }
//
//  }
