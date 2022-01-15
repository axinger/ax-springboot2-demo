package com.ax.mall.advice;


import com.ax.mall.util.axUtil.AxResultEntity;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

@RestControllerAdvice(basePackages = "com.ax.shop.controller")
public class ResultController implements ResponseBodyAdvice<Object> {

//    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseBody.class;

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);

//        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
//        ResponseBody focusController = AnnotationUtils.findAnnotation(annotatedElement, ResponseBody.class);
//        System.out.println("focusController = " + focusController);
//        return focusController != null;

//        return true;
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        System.out.println("body.getClass() = " + body.getClass());
        System.out.println("body = " + body);
        System.out.println("MethodParameter = " + returnType);
        System.out.println("MediaType = " + selectedContentType);

        if (body instanceof Resource) {
            System.out.println("instanceof Resource");

            return body;
        }

        if (selectedContentType == MediaType.TEXT_HTML) {
            System.out.println("MediaType.TEXT_HTML");
            return body;
        }

        //         防止重复包裹的问题出现
        if (body instanceof AxResultEntity) {
            System.out.println("重复包裹");
            return body;
        }

        // 这里 会拦截html
        AxResultEntity entity = AxResultEntity.Success(body);
        System.out.println("返回 entity =======");
        return entity;
    }
}
