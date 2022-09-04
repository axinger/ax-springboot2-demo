package com.axing.common.response.advice;

import com.axing.common.response.exception.ServiceException;
import com.axing.common.response.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author xing
 * ${axing.response.base-packages:} 有问题
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.axing"})
@ResponseBody
public class GlobalException {

    @Value("${axing.response.base-packages:}")
    private String basePackages;

    @PostConstruct
    private void init() {
        log.info("basePackages = {}", basePackages);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Map<String, Object>> exception(HttpServletRequest request, Exception exception) {

        String method = request.getMethod();
        String uri = request.getRequestURI();

        Map<String, Object> map = new HashMap<>(16);
        map.put("method", method);
        map.put("path", uri);

        String msg = null;
        if (Optional.of(exception).map(Throwable::getMessage).isPresent()) {
            msg = exception.getMessage();
        }
        if (Optional.of(exception).map(Throwable::getCause).map(Throwable::getMessage).isPresent()) {
            msg = exception.getCause().getMessage();
        }
        final Result<Map<String, Object>> result = Result.build(201, map, msg);
        log.error("全局异常 result = {}", result);
        return result;
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result serviceException(ServiceException e) {
        final Result<Map<String, Object>> result = Result.build(e.getCode(), e.getMessage());
        log.error("自定义业务异常 result =  {}", result);
        return result;
    }

    /**
     * 对方法参数校验异常处理方法
     */
    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            BindException.class,
            MissingServletRequestParameterException.class,
    })
    public Result handlerNotValidException(Exception validException) {

        List<ObjectError> list = new ArrayList<>();

        if (validException instanceof MethodArgumentNotValidException) {
            list = ((MethodArgumentNotValidException) validException).getBindingResult().getAllErrors();
        } else if (validException instanceof BindException) {
            list = ((BindException) validException).getBindingResult().getAllErrors();
        }

        Map<String, Object> map = new HashMap<>(16);

        list.forEach(error -> {
            String key;
            // 字段错误
            if (error instanceof FieldError) {
                // 获取错误验证字段名
                key = ((FieldError) error).getField();
            } else {
                // 非字段错误
                // 获取验证对象名称
                key = error.getObjectName();
            }
            // 错误信息
            String msg = error.getDefaultMessage();
            map.put(key, msg);
        });

        final Result<Map<String, Object>> result = Result.build(201, map, "方法参数校验异常");
        log.error("方法参数校验异常 result =  {}", result);
        return result;

    }

}
