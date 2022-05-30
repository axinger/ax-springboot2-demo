//package com.ax.demo.config;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StringSerializer;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.text.NumberFormat;
//
//@Configuration
//public class NumberFormatConfig {
//
//    @Bean
//    public NumberFormatCustomizer getNumberFormatCustomizer() {
//        // 配置jackson全局浮点数格式化输出
//        return new NumberFormatCustomizer();
//    }
//
//    static class NumberFormatCustomizer implements Jackson2ObjectMapperBuilderCustomizer {
//
//        @Override
//        public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//            // 配置json序列化
//            // long类型输出字符串
//            // double和BigDecimal保留两位小数截断输出字符串
//            jacksonObjectMapperBuilder
//                    .serializerByType(Long.class, new NumberSerializer())
//                    .serializerByType(Long.TYPE, new NumberSerializer())
//                    .serializerByType(Double.class, new NumberSerializer())
//                    .serializerByType(Double.TYPE, new NumberSerializer())
//                    .serializerByType(Float.class, new NumberSerializer())
//                    .serializerByType(Float.TYPE, new NumberSerializer())
//                    .serializerByType(BigDecimal.class, new NumberSerializer() )
//            ;
//
//        }
//    }
//
//    public static class NumberSerializer extends JsonSerializer<Number> {
//
//        private NumberFormat numberFormat;
//
//        public NumberSerializer() {
//            this.numberFormat = NumberFormat.getInstance();
//            // 最多两位小数
//            this.numberFormat.setMaximumFractionDigits(2);
//            // 截断
//            this.numberFormat.setRoundingMode(RoundingMode.HALF_DOWN);
//        }
//
//        @Override
//        public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//            if (value != null) {
//                gen.writeString(this.numberFormat.format(value));
//            }
//        }
//    }
//}
