package com.axing.demo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exception(HttpServletRequest request, Exception e) {

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
        return map;
    }

    /**
     * 对方法参数校验异常处理方法
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class, MissingServletRequestParameterException.class,})
    public Object handlerNotValidException(Exception e) {

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
        return map;
    }

    /**
     * jsr 规范中的验证异常，嵌套检验问题
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        // String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));

       // ConstraintViolation<?> violation = violations.iterator().next();
       // String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
       // String message = String.format("%s:%s", path, violation.getMessage());
       //
       //  Map<String, Object> map = new HashMap<>(16);
       //  map.put("参数校验错误", message);

        Map<String, Object> map = violations.stream()
                .collect(Collectors.toMap(val -> ((PathImpl) val.getPropertyPath()).getLeafNode().getName(),
                ConstraintViolation::getMessage, (key1, key2) -> key2));
        return map;
    }


}
