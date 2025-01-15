//package com.github.axinger.config;
//
//import com.github.axinger.service.JwtTokenProvider;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//// 获取请求路径
////        String servletPath = request.getServletPath(); // /v1/resource
////        String requestURI = request.getRequestURI();   // /api/v1/resource
////        String contextPath = request.getContextPath(); // /api
////        String queryString = request.getQueryString(); // id=123
//
//
//@Component
//@Slf4j
//@Order
//public class MyFilter implements Filter {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    public static final List<String> WHITELIST = List.of("/login", "/", "/favicon.ico");
//
//
//    public boolean isWhitelisted(String path) {
//        return WHITELIST.stream().anyMatch(path::matches); // 支持通配符匹配
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        log.info("全局Filter==========");
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        String servletPath = request.getServletPath();
//        if (isWhitelisted(servletPath)) {
//            log.info("白名单放行={}", WHITELIST);
//            filterChain.doFilter(request, servletResponse);
//            return;
//        }
//
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/plain;charset=UTF-8");
//        String token = request.getHeader("Authorization");
//        if (ObjectUtils.isEmpty(token)) {
//            // 如果没有提供 Token，返回提示
//            log.warn("未提供 Token");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置 HTTP 状态码 401
//            servletResponse.getWriter().write("未提供 Token");
//        }
//
//        // 检查是否有 token 并验证
//        if (jwtTokenProvider.validateToken(token)) {
//            // 验证成功，设置用户权限并继续过滤链
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            filterChain.doFilter(request, servletResponse);
//            return; // 成功后直接返回，不继续执行后续代码
//        }
//        // Token 验证失败
//        log.warn("Token 验证失败");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置 HTTP 状态码 401
//        servletResponse.getWriter().write("Token 验证失败");
//    }
//}
