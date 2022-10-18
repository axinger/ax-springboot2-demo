package com.axing.common.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;


/**
 * 使用官方自带的json格式类库，fastjson因为content type问题时不时控制台报错、无法直接返回二进制等问题
 */
public class JacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public JacksonHttpMessageConverter(String dateFormat) {

        ObjectMapper objectMapper = new ObjectMapper();
        this.setObjectMapper(objectMapper);

        objectMapper
                .setSerializerFactory(getObjectMapper().getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()))
                // 设置序列化反序列化采用直接处理字段的方式， 不依赖setter 和 getter
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                // 反序列化设置 关闭反序列化时Jackson发现无法找到对应的对象字段，便会抛出UnrecognizedPropertyException: Unrecognized field xxx异常
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 序列化设置 关闭日志输出为时间戳的设置
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

                // 指定时区
                .setTimeZone(TimeZone.getTimeZone("GMT+8"))
                // 日期类型字符串处理
                .setDateFormat(new SimpleDateFormat(dateFormat))
                .registerModule(javaTimeModule(dateFormat)) // 和 findAndRegisterModules 有冲突

        // 自动查找并注册Java 8相关模块
//                .findAndRegisterModules()


        ;


    }

    protected JavaTimeModule javaTimeModule(String dateFormat) {
        // Java8日期日期处理
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer(dateFormat));
        javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer(dateFormat));

        javaTimeModule.addSerializer(LocalDate.class, localDateSerializer("yyyy-MM-dd"));
        javaTimeModule.addDeserializer(LocalDate.class, localDateDeserializer("yyyy-MM-dd"));

        javaTimeModule.addSerializer(LocalTime.class, localTimeSerializer("HH:mm:ss"));
        javaTimeModule.addDeserializer(LocalTime.class, localTimeDeserializer("HH:mm:ss"));
        return javaTimeModule;
    }

    protected LocalDateTimeSerializer localDateTimeSerializer(String dateFormat) {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat));
    }


    protected LocalDateTimeDeserializer localDateTimeDeserializer(String dateFormat) {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat));
    }


    protected LocalDateSerializer localDateSerializer(String pattern) {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(pattern));
    }


    protected LocalDateDeserializer localDateDeserializer(String pattern) {
        return new LocalDateDeserializer(DateTimeFormatter.ofPattern(pattern));
    }

    protected LocalTimeSerializer localTimeSerializer(String pattern) {
        return new LocalTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    protected LocalTimeDeserializer localTimeDeserializer(String pattern) {
        return new LocalTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 处理数组类型的null值
     */
    public static class NullArrayJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value == null) {
                jgen.writeStartArray();
                jgen.writeEndArray();
            }
        }
    }


    /**
     * 处理字符串等类型的null值
     */
    public static class NullStringJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(StringUtils.EMPTY);
        }
    }

    /**
     * 处理字符串等类型的null值
     */
    public static class NullNumberJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(0);
        }
    }

    /**
     * 处理字符串等类型的null值
     */
    public static class NullBooleanJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeBoolean(false);
        }
    }


    public static class MyBeanSerializerModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            //循环所有的beanPropertyWriter
            for (BeanPropertyWriter writer : beanProperties) {
//                BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
                //判断字段的类型，如果是array，list，set则注册nullSerializer
                if (isArrayType(writer)) {
                    //给writer注册一个自己的nullSerializer
                    writer.assignNullSerializer(new NullArrayJsonSerializer());
                } else if (isNumberType(writer)) {
                    writer.assignNullSerializer(new NullNumberJsonSerializer());
                } else if (isBooleanType(writer)) {
                    writer.assignNullSerializer(new NullBooleanJsonSerializer());
                } else if (isStringType(writer)) {
                    writer.assignNullSerializer(new NullStringJsonSerializer());
                }
            }
            return beanProperties;
        }

        /**
         * 是否是数组
         */
        private boolean isArrayType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是string
         */
        private boolean isStringType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
        }


        /**
         * 是否是int
         */
        private boolean isNumberType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return Number.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是boolean
         */
        private boolean isBooleanType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.equals(Boolean.class);
        }
    }


}