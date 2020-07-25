package com.ax.demo.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 因为参数没有用@RequestBody绑定，只打印了ResponseBodyAdvice里的日志，没有进入RequestBodyAdvice。
 */
@Slf4j
@ControllerAdvice
//@RestControllerAdvice
public class MyRequestBodyAdvice implements RequestBodyAdvice {
//    @Override
//    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return true;
//        return GsonHttpMessageConverter.class.isAssignableFrom(aClass);
//    }

    /**
     *  设置条件,这个条件为true才会执行下面的beforeBodyRead方法
     * @param methodParameter
     * @param targetType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {

        //判断是否有此注解,针对所有以@RequestBody的参数
        boolean b = methodParameter.getParameterAnnotation(RequestBody.class) != null;
        //只有为true时才会执行afterBodyRead
        return b;


    }


    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        Method method = methodParameter.getMethod();
        log.info("\n请求参数拦截前 = {}.{}", method.getDeclaringClass().getSimpleName(), method.getName());
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求参数拦截后 = {}.{}:{}", method.getDeclaringClass().getSimpleName(), method.getName(), JSON.toJSONString(o));
        return o;
    }
    /**
     * 传入的json是空值的时候,进入这个方法
     * @param o
     * @param httpInputMessage
     * @param methodParameter
     * @param type
     * @param aClass
     * @return
     */
    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        log.info("请求参数拦截空 = {}.{}", method.getDeclaringClass().getSimpleName(), method.getName());
        return o;
    }



}