package com.axing.common.advice;

import com.axing.common.response.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * 你会发现Swagger3会报Unable to infer base url……的错误，这是因为统一返回体影响到了Swagger3的一些内置接口。
 * 解决方法是@RestControllerAdvice控制好生效的包范围,也就是配置其basePackages参数就行了，这个潜在的冲突浪费我了一个多小时。
 * https://jishuin.proginn.com/p/763bfbd5811f
 *
 * @author xing
 */

/**
 * 为了解决swagger-ui拦截
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalResponse implements ResponseBodyAdvice<Object> {

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return !methodParameter.getDeclaringClass().getName().contains("org.springdoc");
    }


    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof String) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Result.ok(body));
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
