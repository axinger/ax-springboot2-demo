package com.axing.common.model;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 目前LocalTime,LocalDate,无法全局设置,暂不用
 */

public class FastJson2Converter {


    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
//        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
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
        return converter;
    }
}
