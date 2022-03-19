package com.ax.common.service.advice;

import com.ax.common.util.exception.ServiceException;
import com.ax.common.util.result.Result;
import com.ax.common.util.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author xing
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.ax")
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Map<String, Object>> exception(HttpServletRequest request, Exception exception) {

        String method = request.getMethod();
        String uri = request.getRequestURI();

        Map<String, Object> map = new HashMap<>(16);
        map.put("method", method);
        map.put("uri", uri);
        Optional.of(exception).map(Throwable::getMessage).ifPresent(val -> map.put("exceptionMessage", val));

        Optional.of(exception).map(Throwable::getCause).map(Throwable::getMessage)
                .ifPresent(val -> map.put("causeMessage", val));

        final Result<Map<String, Object>> result = Result.build(map, ResultCodeEnum.FAIL);
        log.error("全局异常 result = {}", result);
        return result;
    }

    @ExceptionHandler(value = ServiceException.class)
    public Object serviceException(ServiceException e) {

        final Result<Map<String, Object>> result = Result.build(e.getCode(), e.getMessage());

        log.error("自定义业务异常 result =  {}", result);
        return result;
    }

    /**
     * 对方法参数校验异常处理方法
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class,
            MissingServletRequestParameterException.class,})
    public Object handlerNotValidException(Exception validException) {

        List<ObjectError> list = new ArrayList<>();

        if (validException instanceof MethodArgumentNotValidException) {
            list = ((MethodArgumentNotValidException) validException).getBindingResult().getAllErrors();
        } else if (validException instanceof BindException) {
            list = ((BindException) validException).getBindingResult().getAllErrors();
        }

        Map<String, Object> map = new HashMap<>();

        list.forEach(error -> {
            String key;
            // 字段错误
            if (error instanceof FieldError) {
                key = ((FieldError) error).getField();// 获取错误验证字段名
            } else {
                // 非字段错误
                key = error.getObjectName();// 获取验证对象名称
            }
            // 错误信息
            String msg = error.getDefaultMessage();
            map.put(key, msg);
        });

        final Result<Map<String, Object>> result = Result.fail(map);

        log.error("方法参数校验异常 result =  {}", result);
        return result;

    }

}
