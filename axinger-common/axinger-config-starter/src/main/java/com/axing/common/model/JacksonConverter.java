package com.axing.common.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.ZoneId;
import java.util.*;


/**
 * 使用官方自带的json格式类库，fastjson因为content type问题时不时控制台报错、无法直接返回二进制等问题
 */
public class JacksonConverter extends MappingJackson2HttpMessageConverter {

    public JacksonConverter(JacksonProperties jacksonProperties) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.setObjectMapper(objectMapper);

        // 序列化枚举值
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        //忽略value为null时key的输出
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 序列化设置 关闭日志输出为时间戳的设置
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 反序列化设置 关闭反序列化时Jackson发现无法找到对应的对象字段，便会抛出UnrecognizedPropertyException: Unrecognized field xxx异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        objectMapper.setLocale(Locale.CHINA);

        // 设置序列化反序列化采用直接处理字段的方式， 不依赖setter 和 getter
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);


        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));


        List<SimpleModule> moduleList = new ArrayList<>();

        //序列化成json时，将所有的Long变成string，以解决js中的精度丢失。
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        moduleList.add(simpleModule)
        String dateFormat = Optional.ofNullable(jacksonProperties).map(JacksonProperties::getDateFormat).orElse("yyyy-MM-dd HH:mm:ss");
        Java8TimeModule java8TimeModule = new Java8TimeModule();
        moduleList.add(java8TimeModule)
        ;

        objectMapper.registerModules(moduleList);


    }
}