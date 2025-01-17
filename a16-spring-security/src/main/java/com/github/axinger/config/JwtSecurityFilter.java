package com.github.axinger.config;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjUtil;
import com.axing.common.response.dto.Result;
import com.axing.common.util.utils.ResponseUtil;
import com.github.axinger.service.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws IOException, ServletException {
        boolean whitelisted = SecurityWhitelist.isWhitelisted(request.getRequestURI());
        if (whitelisted) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization");
        if (Objects.isNull(token)) {
            ResponseUtil.writeError(response, Result.fail("没有token"));
            return;
        }

        if (ObjUtil.isEmpty(jwtTokenProvider.validateToken(token))) {
            ResponseUtil.writeError(response, Result.fail("token验证失败"));
            return;
        }
        try {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {

            ResponseUtil.writeError(response, Result.fail(ExceptionUtil.getSimpleMessage(e)));
            return;
        }

        // 放行
        filterChain.doFilter(request, response);
    }
}
