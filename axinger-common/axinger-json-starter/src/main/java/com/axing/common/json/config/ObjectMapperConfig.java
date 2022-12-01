package com.axing.common.json.config;

import com.axing.common.json.model.Java8TimeModule;
import com.axing.common.json.model.MyBeanSerializerModifier;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
@Slf4j
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();

        // 序列化枚举值
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        //忽略value为null时key的输出
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //
        // 序列化设置 关闭日志输出为时间戳的设置
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 反序列化设置 关闭反序列化时Jackson发现无法找到对应的对象字段，便会抛出UnrecognizedPropertyException: Unrecognized field xxx异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        objectMapper.setLocale(Locale.CHINA);

        // 设置序列化反序列化采用直接处理字段的方式， 不依赖setter 和 getter
        //objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        //objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(simpleDateFormat);

        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));

        List<SimpleModule> moduleList = new ArrayList<>();

        //序列化成json时，将所有的Long变成string，以解决js中的精度丢失。
        //SimpleModule simpleModule = new SimpleModule();
        //simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        //simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        //moduleList.add(simpleModule)
        Java8TimeModule java8TimeModule = new Java8TimeModule();
        moduleList.add(java8TimeModule);

        objectMapper.registerModules(moduleList);


        // 解决redis 反序列化问题, 自定义需要,需要绑定对象类型,不方便通用
        // 都序列化成map
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //objectMapper.activateDefaultTyping(
        //        LaissezFaireSubTypeValidator.instance,
        //        ObjectMapper.DefaultTyping.NON_FINAL,
        //        JsonTypeInfo.As.PROPERTY);

        return objectMapper;
    }


}