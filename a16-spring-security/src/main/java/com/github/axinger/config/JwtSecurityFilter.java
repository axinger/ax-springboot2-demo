package com.github.axinger.config;

import com.github.axinger.service.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        try {
            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
                // 验证成功，设置用户权限并继续过滤链
                try {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }catch (RuntimeException e){
                    System.out.println("e = " + e);
                }

            }
        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
            // 根据实际需求选择是否响应客户端或继续过滤链
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
