package com.ax.demo.advice;


import com.ax.demo.util.axUtil.AxResultEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

/**
 * 你会发现Swagger3会报Unable to infer base url……的错误，这是因为统一返回体影响到了Swagger3的一些内置接口。
 * 解决方法是@RestControllerAdvice控制好生效的包范围,也就是配置其basePackages参数就行了，这个潜在的冲突浪费我了一个多小时。
 * https://jishuin.proginn.com/p/763bfbd5811f
 */
@RestControllerAdvice(basePackages = "com.ax.demo.controller")
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {

//    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseBody.class;

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);

//        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
//        ResponseBody focusController = AnnotationUtils.findAnnotation(annotatedElement, ResponseBody.class);
//        System.out.println("focusController = " + focusController);
//        return focusController != null;
        return true;
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        System.out.println("body.getClass() = " + body.getClass());
        System.out.println("body = " + body);
        System.out.println("MethodParameter = " + returnType);
        System.out.println("MediaType = " + selectedContentType);


        if (!selectedContentType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)) {

            return body;
        }

        if (body instanceof Resource) {
            return body;
        }

        if (selectedContentType == MediaType.TEXT_HTML) {
            System.out.println("selectedContentType == MediaType.TEXT_HTML");
            return body;
        }

        if (selectedContentType == MediaType.parseMediaType(MediaType.TEXT_HTML_VALUE)) {
            return body;
        }

        //处理返回值是String的情况
        if (body instanceof String) {
            System.out.println("body instanceof String = " + body);
            String string = (String) body;
            AxResultEntity<String> entity = AxResultEntity.Success(string);
            return entity;
        }

        //         防止重复包裹的问题出现
        if (body instanceof AxResultEntity) {
            return body;
        }
        System.out.println("封装返回结果 = " + body);
        // 这里 会拦截html,比如  Swagger3
        return AxResultEntity.Success(body);
//        return body;

    }

}
