package com.axing.common.response.advice;

import com.alibaba.fastjson2.JSON;
import com.axing.common.response.result.Result;
import lombok.extern.slf4j.Slf4j;
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

/**
 * 你会发现Swagger3会报Unable to infer base url……的错误，这是因为统一返回体影响到了Swagger3的一些内置接口。
 * 解决方法是@RestControllerAdvice控制好生效的包范围,也就是配置其basePackages参数就行了，这个潜在的冲突浪费我了一个多小时。
 * https://jishuin.proginn.com/p/763bfbd5811f
 *
 * @author xing
 */

@Slf4j
/**
 * 为了解决swagger-ui拦截
 */
@RestControllerAdvice(basePackages = {"com.axing"})
@ResponseBody
public class GlobalResponse implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseBody.class;


    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE)
                || returnType.hasMethodAnnotation(ANNOTATION_TYPE);

//        return  AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);

    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof String) {
            return JSON.toJSONString(Result.ok(body));
        }

        if (body instanceof Resource) {
            return body;
        }

        if (selectedContentType == MediaType.TEXT_HTML) {
            return body;
        }

        /// 防止重复包裹的问题出现
        if (body instanceof Result) {
            return body;
        }

        if (body instanceof Boolean) {
            boolean res = (boolean) body;
            if (res) {
                return Result.ok();
            } else {
                return Result.fail();
            }
        }
        return Result.ok(body);
    }
}
