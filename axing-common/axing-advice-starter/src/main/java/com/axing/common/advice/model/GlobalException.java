package com.axing.common.advice.model;

import com.axing.common.advice.bean.AdviceProperties;
import com.axing.common.response.exception.ServiceException;
import com.axing.common.response.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

/**
 * @author xing
 * ${axing.response.base-packages:} 有问题
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {

    private final AdviceProperties adviceProperties;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Map<String, Object>> exception(HttpServletRequest request, Exception e) {

        if (adviceProperties.isPrintStackTrace()) {
            e.printStackTrace();
        }

        String method = request.getMethod();
        String uri = request.getRequestURI();

        Map<String, Object> map = new HashMap<>(16);
        map.put("method", method);
        map.put("path", uri);

        String msg = "";
        if (Optional.of(e).map(Throwable::getMessage).isPresent()) {
            msg = e.getMessage();
        } else if (Optional.of(e).map(Throwable::getCause).map(Throwable::getMessage).isPresent()) {
            msg = e.getCause().getMessage();
        }
        final Result<Map<String, Object>> result = Result.build(201, map, msg);
        log.error("全局异常 result = {}, e = {}", result, e.getMessage());
        return result;
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result<Map<String, Object>> serviceException(ServiceException e) {
        if (adviceProperties.isPrintStackTrace()) {
            e.printStackTrace();
        }
        final Result<Map<String, Object>> result = Result.build(e.getCode(), e.getMessage());
        log.error("自定义业务异常 result =  {}", result);
        return result;
    }

    /**
     * 对方法参数校验异常处理方法
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class, MissingServletRequestParameterException.class,})
    public Result<Map<String, Object>> handlerNotValidException(Exception e) {
        if (adviceProperties.isPrintStackTrace()) {
            e.printStackTrace();
        }
        List<ObjectError> list = new ArrayList<>();

        if (e instanceof MethodArgumentNotValidException validException) {
            list = Optional.ofNullable(validException).map(MethodArgumentNotValidException::getBindingResult).map(BindingResult::getAllErrors).orElse(new ArrayList<>());
        } else if (e instanceof BindException bindException) {
            list = Optional.ofNullable(bindException).map(BindingResult::getAllErrors).orElse(new ArrayList<>());
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
