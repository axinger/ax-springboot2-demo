package com.axing.common.json.config;

import com.axing.common.json.bean.JsonProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@Configuration
@EnableConfigurationProperties(JsonProperties.class)
// @Import(Jackson2ObjectMapperBuilder.class)
// @AutoConfigureBefore(JacksonAutoConfiguration.class)
// @ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JsonAutoConfig {

    @Autowired
    private JsonProperties jsonProperties;


    // localDateTime 序列化器
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(jsonProperties.getDateFormat()));
    }

    // localDateTime 反序列化器
    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getDateFormat()));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            // builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.locale(Locale.CHINA);
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);

            builder.simpleDateFormat(jsonProperties.getDateFormat());

            // 使用这个方式, 使用modules,会影响其他设置 builder.modules(new Java8TimeModule());
            // yyyy-MM-dd HH:mm:ss
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());

            // yyyy-MM-dd
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalDateFormat())));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalDateFormat())));

            // HH:mm:ss
            builder.serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalTimeFormat())));
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalTimeFormat())));

        };
    }
    //
    // @Bean
    // public ObjectMapper objectMapper() {
    //
    //     ObjectMapper objectMapper = new ObjectMapper();
    //
    //     // 序列化枚举值
    //     objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    //
    //     // 忽略value为null时key的输出
    //     objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    //     //
    //     // 序列化设置 关闭日志输出为时间戳的设置
    //     objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    //     // 反序列化设置 关闭反序列化时Jackson发现无法找到对应的对象字段，便会抛出UnrecognizedPropertyException: Unrecognized field xxx异常
    //     objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    //     objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
    //     objectMapper.setLocale(Locale.CHINA);
    //
    //     // 设置序列化反序列化采用直接处理字段的方式， 不依赖setter 和 getter
    //     // objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
    //     // objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    //     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //     objectMapper.setDateFormat(simpleDateFormat);
    //
    //     objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
    //
    //     List<SimpleModule> moduleList = new ArrayList<>();
    //
    //     // 序列化成json时，将所有的Long变成string，以解决js中的精度丢失。
    //     // SimpleModule simpleModule = new SimpleModule();
    //     // simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    //     // simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    //     // moduleList.add(simpleModule)
    //     // Java8TimeModule java8TimeModule = new Java8TimeModule();
    //     // moduleList.add(java8TimeModule);
    //     //
    //     // objectMapper.registerModules(moduleList);
    //
    //     // 自动查找并注册Java 8相关模块
    //     objectMapper.findAndRegisterModules();
    //
    //
    //     // 解决redis 反序列化问题, 自定义需要,需要绑定对象类型,不方便通用
    //     // 都序列化成map
    //     // om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //     // objectMapper.activateDefaultTyping(
    //     //        LaissezFaireSubTypeValidator.instance,
    //     //        ObjectMapper.DefaultTyping.NON_FINAL,
    //     //        JsonTypeInfo.As.PROPERTY);
    //
    //     return objectMapper;
    // }


}
