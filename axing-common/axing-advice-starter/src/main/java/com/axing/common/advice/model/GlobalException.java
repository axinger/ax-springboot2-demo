package com.axing.common.advice.model;

import com.axing.common.advice.bean.AdviceProperties;
import com.axing.common.response.exception.ServiceException;
import com.axing.common.response.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.stream.Collectors;

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
    public Result<Object> handlerNotValidException(Exception e) {
        if (adviceProperties.isPrintStackTrace()) {
            e.printStackTrace();
        }
        List<ObjectError> list = new ArrayList<>();

        if (e instanceof MethodArgumentNotValidException validException) {
            list = Optional.of(validException).map(MethodArgumentNotValidException::getBindingResult).map(BindingResult::getAllErrors).orElse(new ArrayList<>());
        } else if (e instanceof BindException bindException) {
            list = Optional.of(bindException).map(BindingResult::getAllErrors).orElse(new ArrayList<>());
        }

        Map<String, Object> map = list.stream().collect(Collectors.toMap(error -> {
            if (error instanceof FieldError error1) {
                // 获取错误验证字段名
                return error1.getField();
            } else {
                // 非字段错误
                // 获取验证对象名称
                return error.getObjectName();
            }
        }, error -> Optional.of(error).map(ObjectError::getDefaultMessage).orElse(""), (key1, key2) -> key2));
        // final Result<Map<String, Object>> result = Result.build(201, map, "参数校验异常");
        Result<Object> result = Result.failMessage(map.toString());
        // Result<Object> result = Result.fail(map);
        log.error("方法参数校验异常 result =  {}", result);
        return result;
    }

}
