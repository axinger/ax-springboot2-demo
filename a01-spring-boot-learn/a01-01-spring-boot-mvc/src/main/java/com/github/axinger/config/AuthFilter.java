package com.github.axinger.config;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter初始化
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("容器级别,优先走1111Filter===============================");

        MyHolder.setUserId(IdUtil.fastSimpleUUID());
        MyTrackId.setId(IdUtil.getSnowflakeNextId());

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取请求中的token
//        String token = httpRequest.getHeader("Authorization");
//
//
//        System.out.println("token = " + token);


        // 如果token有效，继续请求
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 资源释放
        MyHolder.removeUserId();
        MyTrackId.removeId();
    }

    // 模拟token验证方法
    private boolean isValidToken(String token) {
        return token.equals("valid-token"); // 替换为实际的token验证逻辑
    }
}
