package com.axing.common.json.config;

import com.axing.common.json.bean.JsonProperties;
import com.axing.common.json.model.Java8TimeModule;
import com.axing.common.json.model.MyBeanSerializerModifier;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
@Configuration
@EnableConfigurationProperties(JsonProperties.class)
// @Import(Jackson2ObjectMapperBuilder.class)
// @AutoConfigureBefore(JacksonAutoConfiguration.class)
// @ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class ObjectMapperConfig {

    @Autowired
    private JsonProperties jsonProperties;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            // builder.serializerByType(Long.class, ToStringSerializer.instance);


            builder.locale(Locale.CHINA);
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);

            builder.simpleDateFormat(jsonProperties.getDateFormat());

            // 使用这个方式, 使用modules,会影响其他设置 builder.modules(new Java8TimeModule());
            // builder.modules(new Java8TimeModule(jsonProperties));
            // yyyy-MM-dd HH:mm:ss
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(jsonProperties.getDateFormat())));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getDateFormat())));

            // yyyy-MM-dd
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalDateFormat())));
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalDateFormat())));

            // HH:mm:ss
            builder.serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalTimeFormat())));
            builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getLocalTimeFormat())));
        };
    }

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化枚举值
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        // 忽略value为null时key的输出
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //
        // 序列化设置 关闭日志输出为时间戳的设置
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 反序列化设置 关闭反序列化时Jackson发现无法找到对应的对象字段，便会抛出UnrecognizedPropertyException: Unrecognized field xxx异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        objectMapper.setLocale(Locale.CHINA);

        // 设置序列化反序列化采用直接处理字段的方式， 不依赖setter 和 getter
        // objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        // objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(jsonProperties.getDateFormat());
        objectMapper.setDateFormat(simpleDateFormat);

        objectMapper.registerModule(new Java8TimeModule(jsonProperties));
        // 自定义
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
        return objectMapper;
    }
}
