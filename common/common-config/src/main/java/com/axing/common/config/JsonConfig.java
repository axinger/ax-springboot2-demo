package com.axing.common.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName FastJsonConfig.java
 * @description https://github.com/alibaba/fastjson2/blob/main/docs/features_cn.md
 * @createTime 2022年09月21日 20:01:00
 */
@Configuration
public class JsonConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverter() {


        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setReaderFeatures(
                JSONReader.Feature.FieldBased,
                JSONReader.Feature.SupportArrayToBean,
                JSONReader.Feature.SupportAutoType,
                JSONReader.Feature.AllowUnQuotedFieldNames,
                JSONReader.Feature.SupportAutoType,
                JSONReader.Feature.UseDefaultConstructorAsPossible,
                JSONReader.Feature.SupportSmartMatch
        );
        config.setWriterFeatures(
                //基于字段反序列化，如果不配置，会默认基于public的field和getter方法序列化。
                // 配置后，会基于非static的field（包括private）做反序列化。在fieldbase配置下会更安全
                JSONWriter.Feature.FieldBased,

                JSONWriter.Feature.WriteMapNullValue,
                //JSONWriter.Feature.PrettyFormat,//格式化输出
                JSONWriter.Feature.WriteNullListAsEmpty,//将List类型字段的空值序列化输出为空数组"[]"
                JSONWriter.Feature.WriteNullStringAsEmpty, // 将String类型字段的空值序列化输出为空字符串""
                JSONWriter.Feature.WriteNullNumberAsZero, //将Number类型字段的空值序列化输出为0
                JSONWriter.Feature.WriteNullBooleanAsFalse, // 将Boolean类型字段的空值序列化输出为false
                JSONWriter.Feature.NotWriteEmptyArray,//数组类型字段当length为0时不输出
                JSONWriter.Feature.WriteNonStringKeyAsString, //将Map中的非String类型的Key当做String类型输出
                JSONWriter.Feature.ErrorOnNoneSerializable //序列化非Serializable对象时报错

                //JSONWriter.Feature.ReferenceDetection
        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        // 4.中文乱码解决方案
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(supportedMediaTypes);


        // 自定义LocalDate序列化格式
        JSON.register(LocalDate.class, (jsonWriter, object, fieldName, fieldType, features) -> {
            if (object == null) {
                jsonWriter.writeNull();
                return;
            }
            LocalDate localDate = (LocalDate) object;
            jsonWriter.writeString(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        });

        // 自定义LocalTime序列化格式
        JSON.register(LocalTime.class, (jsonWriter, object, fieldName, fieldType, features) -> {
            if (object == null) {
                jsonWriter.writeNull();
                return;
            }
            LocalTime localTime = (LocalTime) object;
            jsonWriter.writeString(localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        });
        return new HttpMessageConverters(converter);
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        // 指定时区
//        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        // 日期类型字符串处理
////        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_FORMATTER));
//
//        // Java8日期日期处理
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DatePattern.NORM_DATETIME_FORMATTER));
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DatePattern.NORM_DATE_FORMATTER));
//        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DatePattern.NORM_TIME_FORMATTER));
//
//
//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DatePattern.NORM_DATETIME_FORMATTER));
//        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DatePattern.NORM_DATE_FORMATTER));
//        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DatePattern.NORM_TIME_FORMATTER));
//        objectMapper.registerModule(javaTimeModule);
//
//        converter.setObjectMapper(objectMapper);
//        return converter;
//    }

}
