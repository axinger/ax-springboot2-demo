//package com.ax.demo.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.TimeZone;
//
//@Configuration
//public class DateConfig {
//
//    @Value("${spring.jackson.date-format:HH:mm:ss}")
//    private String timePattern;
//
//    @Value("${spring.jackson.date-format:yyyy-MM-dd}")
//    private String datePattern;
//
//    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
//    private String dateTimePattern;
//
//    @Bean
//    public LocalTimeSerializer localTimeDeserializer() {
//        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(timePattern));
//    }
//
//    @Bean
//    public LocalDateSerializer localDateDeserializer() {
//        return new LocalDateSerializer(DateTimeFormatter.ofPattern(datePattern));
//    }
//
//    @Bean
//    public LocalDateTimeSerializer localDateTimeDeserializer() {
//        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern));
//    }
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer localTimeFormatBuilderCustomizer() {
//        return builder -> {
//
//            builder.serializerByType(LocalTime.class, localTimeDeserializer());
//            builder.serializerByType(LocalDate.class, localDateDeserializer());
//            builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
//        };
//    }
////
////
////    @Bean
////    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
////        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
////        ObjectMapper objectMapper = new ObjectMapper();
////        // 指定时区
////        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
////        // 日期类型字符串处理
////        objectMapper.setDateFormat(new SimpleDateFormat(dateTimePattern));
////
////        // Java8日期日期处理
////        JavaTimeModule javaTimeModule = new JavaTimeModule();
////        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern)));
////        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(datePattern)));
////        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(timePattern)));
////
////        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimePattern)));
////        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(datePattern)));
////        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timePattern)));
////        objectMapper.registerModule(javaTimeModule);
////
////        converter.setObjectMapper(objectMapper);
////        return converter;
////    }
//
//}
