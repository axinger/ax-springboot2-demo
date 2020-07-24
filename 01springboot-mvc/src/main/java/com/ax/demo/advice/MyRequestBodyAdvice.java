package com.ax.demo.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 因为参数没有用@RequestBody绑定，只打印了ResponseBodyAdvice里的日志，没有进入RequestBodyAdvice。
 */
@Slf4j
//@ControllerAdvice
@RestControllerAdvice
public class MyRequestBodyAdvice implements RequestBodyAdvice {
//    @Override
//    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return true;
//        return GsonHttpMessageConverter.class.isAssignableFrom(aClass);
//    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        /**
         * 系统使用的是Gson作为json数据的Http消息转换器
         */
        return GsonHttpMessageConverter.class.isAssignableFrom(converterType);
    }


    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        Method method = methodParameter.getMethod();
        log.info("请求参数拦截前 = {}.{}", method.getDeclaringClass().getSimpleName(), method.getName());
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求参数拦截后 = {}.{}:{}", method.getDeclaringClass().getSimpleName(), method.getName(), JSON.toJSONString(o));
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求参数拦截空 = {}.{}", method.getDeclaringClass().getSimpleName(), method.getName());
        return o;
    }
}