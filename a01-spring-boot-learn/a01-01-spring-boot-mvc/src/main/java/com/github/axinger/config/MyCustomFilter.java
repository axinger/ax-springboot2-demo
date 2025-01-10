package com.github.axinger.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyCustomFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 只会在这个请求第一次到达时执行这段逻辑
        String userId = "test-user-id";
        // 设置 userId 或者其他处理逻辑

        System.out.println("userId = " + userId);


        filterChain.doFilter(request, response);
    }

    @Override
    protected String getAlreadyFilteredAttributeName() {
        return super.getAlreadyFilteredAttributeName(); // 可以重写返回自定义的属性名
    }
}
