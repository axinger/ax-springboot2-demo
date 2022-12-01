package com.axing.demo.apo;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Aspect
@Slf4j
@Component
class WebLogAspect {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * ============================================================================
     */
    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMappingPointcut() {
    }

    @Before("requestMappingPointcut()")
    public void requestMappingPointcutBefore(JoinPoint joinPoint) {
        logRequestInfo(joinPoint);
    }

    @AfterReturning(pointcut = "requestMappingPointcut()", returning = "resultVo")
    public void requestMappingPointcutAfterReturning(JoinPoint joinpoint, Object resultVo) {
        logResultInfo(joinpoint, resultVo);
    }

    /**
     * ============================================================================
     */


    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingPointcut() {
    }

    @Before("getMappingPointcut()")
    public void getMappingPointcutBefore(JoinPoint joinPoint) {
        //logRequestInfo(joinPoint);
        try {
            getRequestParams(joinPoint);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterReturning(pointcut = "getMappingPointcut()", returning = "resultVo")
    public void getMappingPointcutAfterReturning(JoinPoint joinpoint, Object resultVo) {
        logResultInfo(joinpoint, resultVo);
    }


    /**
     * ============================================================================
     */

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingPointcut() {
    }

    @Before("postMappingPointcut()")
    public void postMappingPointcutBefore(JoinPoint joinPoint) {
        logRequestInfo(joinPoint);
    }

    @AfterReturning(pointcut = "postMappingPointcut()", returning = "resultVo")
    public void postMappingPointcutAfterReturning(JoinPoint joinpoint, Object resultVo) {
        logResultInfo(joinpoint, resultVo);
    }

    /**
     * ============================================================================
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMappingPointcut() {
    }

    @Before("putMappingPointcut()")
    public void putMappingPointcutBefore(JoinPoint joinPoint) {
        logRequestInfo(joinPoint);
    }

    @AfterReturning(pointcut = "putMappingPointcut()", returning = "resultVo")
    public void putMappingPointcutAfterReturning(JoinPoint joinpoint, Object resultVo) {
        logResultInfo(joinpoint, resultVo);
    }

    /**
     * ============================================================================
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMappingPointcut() {
    }

    @Before("deleteMappingPointcut()")
    public void deleteMappingPointcutBefore(JoinPoint joinPoint) {
        logRequestInfo(joinPoint);
    }

    @AfterReturning(pointcut = "deleteMappingPointcut()", returning = "resultVo")
    public void deleteMappingPointcutAfterReturning(JoinPoint joinpoint, Object resultVo) {
        logResultInfo(joinpoint, resultVo);
    }

    /**
     * ============================================================================
     */

    public void logRequestInfo(JoinPoint joinpoint) {
        //方法前打印方法名称和方法参数
        try {
            String[] paramNames = ((MethodSignature) joinpoint.getSignature()).getParameterNames();
            Object[] paramValues = joinpoint.getArgs();
            int paramLength = null == paramNames ? 0 : paramNames.length;
            StringBuilder requestInfo = new StringBuilder();
            if (paramLength == 0) {
                requestInfo.append("{} ");
            } else {
                requestInfo.append("[");
                for (int i = 0; i < paramLength; i++) {
                    if (paramValues[i] instanceof MultipartFile
                            || paramValues[i] instanceof HttpServletRequest
                            || paramValues[i] instanceof HttpServletResponse) {
                        continue;
                    }
                    requestInfo.append(paramNames[i]).append("=").append(JSONObject.toJSONString(paramValues[i]));
                }
                requestInfo.append("]");
            }
            log.info("请求参数 方法名 = {}, 参数 = {}", joinpoint.getSignature().toShortString(), requestInfo);
        } catch (Exception e) {
            log.error("请求参数解析异常: 方法名: {}, e = {}", joinpoint.getSignature().toShortString(), e);
        }
    }

    public void logResultInfo(JoinPoint joinpoint, Object resultVo) {
        //方法后打印方法名称和方法返回值
        try {
            if (null != resultVo) {
                log.info("返回参数 方法名 = {}, 返回值 = {}",joinpoint.getSignature().toShortString(),resultVo);
            }
        } catch (Exception e) {
            log.error("返回参数解析异常: 方法名: {}, e = {}", joinpoint.getSignature().toShortString(), e);
        }
    }

    //获取请求的相关信息
    private Map<String, Object> getRequestParams(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Map<String, Object> requestParams = new HashMap<>();
        //获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        requestParams.put("uri", request.getRequestURI());
        // 获取请求头信息,需要注意的是，请求头中的key都会转成小写
        Enumeration<String> enumeration = request.getHeaderNames();
        JSONObject headers = new JSONObject();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            headers.put(name, value);
        }
        requestParams.put("headers", headers);
        //获取请求的方法
        String method = request.getMethod();
        requestParams.put("method", method);
        List<String> params = new ArrayList<>();
        if (HttpMethod.GET.toString().equals(method)) {// get请求
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString)) {
                params.add(0, URLDecoder.decode(queryString, "UTF-8"));
            }
        } else {//其他请求
            Object[] paramsArray = joinPoint.getArgs();
            if (paramsArray != null && paramsArray.length > 0) {
                for (int i = 0; i < paramsArray.length; i++) {
                    if (paramsArray[i] instanceof Serializable) {
                        params.add(paramsArray[i].toString());
                    } else {
                        try {
                            //使用json系列化 反射等等方法 反系列化会影响请求性能建议重写toString方法实现系列化接口
                            String param = objectMapper.writeValueAsString(paramsArray[i]);
                            if (StringUtils.isNotBlank(param)) {
                                params.add(param);
                            }
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                            log.error("json序列化异常", e);
                        }
                    }
                }
            }
        }

        log.info(">>>>>>uri: {},method: {}", request.getRequestURI(), method);
        log.info(">>>>>>headers: {}", headers);
        log.info(">>>>>>params: {}", params);
        requestParams.put("params", params);
        return requestParams;
    }
}
