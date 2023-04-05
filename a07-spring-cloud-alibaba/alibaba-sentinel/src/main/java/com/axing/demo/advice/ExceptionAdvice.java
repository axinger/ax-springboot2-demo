package com.axing.demo.advice;

import cn.hutool.core.util.ObjectUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(value = Exception.class)// 异常全局处理
    @ResponseBody
//	 在@RequestMapping执行后执行
    public Object exception(HttpServletRequest request,
                            HttpServletResponse response,
                            Exception e) {

        System.out.println("全局exception = " + e);
        System.out.println("全局exception getMessage = " + e.getCause().getMessage());

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String message = e.getCause().getMessage();

        Map<String, Object> map = new HashMap<>();
        map.put("method", method);
        map.put("uri", uri);

        AxResultEntity<Map> entity = new AxResultEntity<>();
        entity.setMsg(map.toString());
        entity.setBody(map);
        entity.setCode(400);
        if (ObjectUtil.isNotEmpty(message)) {
            entity.setMsg(message);
            return entity;
        }


        /**
         * 404处理
         * */
        if (e instanceof NoHandlerFoundException) {
            entity.setStateEnum(AxResultStateEnum.INVALID_METHOD_NOT_FOUND);
            return entity;
        }

        if (e instanceof HttpMediaTypeNotSupportedException) {
            entity.setStateEnum(AxResultStateEnum.INVALID_PARAMETER_FORMAT);
            return entity;
        }
//        /**
//         * token 无效
//         */
//        if (exception instanceof TokenException) {
//            entity.setStateEnum(AxResultStateEnum.TOKEN_INVALID);
//            return entity;
//        }
//
//        /**
//         * SQL异常
//         */
//        if (exception instanceof org.springframework.jdbc.BadSqlGrammarException) {
//            entity.setStateEnum(AxResultStateEnum.INTERNAL_SQL_ERROR);
//            return entity;
//        }

//        AccessDeniedException 局部代码无法返回,这里返回
//        if (exception instanceof org.springframework.security.access.AccessDeniedException) {
//            entity.setStateEnum(AxResultStateEnum.INVALID_ACCESS_DENIED);
//            return entity;
//        }


//        if (exception instanceof org.springframework.security.core.AuthenticationException) {
//            entity.setStateEnum(AxResultStateEnum.INVALID_ACCESS_DENIED);
//            return entity;
//        }


        entity.setStateEnum(AxResultStateEnum.INVALID);
        return entity;
    }


//    @ExceptionHandler(value = AccessDeniedException.class)
//    public Object accessDeniedException(AccessDeniedException validException) {
//        AxResultEntity<Object> entity = new AxResultEntity();
//        entity.setStateEnum(AxResultStateEnum.INVALID_ANONYMOUS_NOT_SECURITY);
//
//        return entity;
//    }

    /**
     * 对方法参数校验异常处理方法
     */
    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            BindException.class, /***/
            MissingServletRequestParameterException.class,/**@RequestParam 校验*/
    })
    public Object handlerNotValidException(Exception validException) {

        System.out.println("对方法参数校验异常处理方法exception = " + validException);

        List<ObjectError> objectErrorList = new LinkedList<>();

        if (validException instanceof MethodArgumentNotValidException) {
            objectErrorList = ((MethodArgumentNotValidException) validException).getBindingResult().getAllErrors();
        } else if (validException instanceof BindException) {
            objectErrorList = ((BindException) validException).getBindingResult().getAllErrors();
        }

        Map<String, Object> map = new HashMap<>();

        objectErrorList.forEach(error -> {
            String key = null;
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


//        for (ObjectError oe : objectErrorList) {
//            String key = null;
//            // 字段错误
//            if (oe instanceof FieldError) {
//                key = ((FieldError) oe).getField();// 获取错误验证字段名
//            } else {
//                // 非字段错误
//                key = oe.getObjectName();// 获取验证对象名称
//            }
//            // 错误信息
//            String msg = oe.getDefaultMessage();
//            map.put(key, msg);
//        }

        AxResultEntity entity = new AxResultEntity();
        entity.setStateEnum(AxResultStateEnum.FAILURE);
        entity.setMsg(map.toString());

        return entity;

    }


}
