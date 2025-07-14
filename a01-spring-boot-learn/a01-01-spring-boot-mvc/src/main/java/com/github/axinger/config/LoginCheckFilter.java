package com.github.axinger.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @WebFilter和@Component一起使用导致urlPatterns不起作用
 * 只使用一个, 但是@Order 又不生效
 */
@Slf4j
//@Component
//@WebFilter(urlPatterns = "/test/**") //拦截所有请求
@javax.servlet.annotation.WebFilter(urlPatterns = "/test/*", dispatcherTypes = {DispatcherType.REQUEST})
@Order(-1)
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        //前置：强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("LoginCheckFilter===============================================================================");
        //6.放行
        chain.doFilter(request, response);
    }
}
