package com.ax.demo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName JsonConfig1.java
 * @description TODO
 * @createTime 2022年05月30日 11:27:00
 */
@Configuration
public class JsonConfig1 {


    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverter() {


        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig config = new FastJsonConfig();

        config.setDateFormat("yyyy-MM月-dd日 HH:MM:ss");

        config.setSerializerFeatures(

                //结果是否格式化,默认为false
                SerializerFeature.PrettyFormat,
                //枚举值使用名称或toSting
                SerializerFeature.WriteEnumUsingName,
                // 保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的null转成""
                SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的null转成0
                SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的null转成[], List<String> list = new ArrayList<>(); 泛型不支持
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的null转成false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect);


        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(config);


        // 4.中文乱码解决方案
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(mediaTypes);

        return fastConverter;

    }

}
