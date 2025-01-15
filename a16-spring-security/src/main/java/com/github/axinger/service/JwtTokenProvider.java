package com.github.axinger.service;

import com.axing.common.util.jwt.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class JwtTokenProvider {


    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = JwtHelper.createToken("", userDetails.getUsername());
        log.info("创建token={}", token);
        return token;
    }

    public boolean validateToken(String token) {
        return JwtHelper.validateToken(token);
    }

    @Autowired
    private UserDetailsService userDetailsService;
//
//    public Authentication getAuthentication(String token) {
//        String username = JwtHelper.getUserName(token);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
//    }

    public Authentication getAuthentication(String token) {
        String username = JwtHelper.getUserName(token); // 获取用户名

        // 使用从 token 中提取的用户名构造 Authentication，而不是再调用 userDetailsService
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

}
