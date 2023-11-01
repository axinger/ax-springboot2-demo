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
import java.util.Optional;

@Slf4j
@Configuration
@EnableConfigurationProperties(JsonProperties.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class ObjectMapperConfig {

    @Autowired
    private JsonProperties jsonProperties;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);

            builder.simpleDateFormat(jsonProperties.getDateFormat());

            if (Optional.ofNullable(jsonProperties).isPresent()) {
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
            }

        };
    }

}
