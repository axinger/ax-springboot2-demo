package com.github.axinger.handler;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class CustomAccessDecisionVoter implements AccessDecisionVoter<Object> {

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
//        System.out.println("object = " + targetUrl);

//      if (targetUrl instanceof HttpServletRequest) {
//
//          System.out.println("authentication = " + authentication);
//      }
//        if (!(targetUrl instanceof String)) {
//            return ACCESS_ABSTAIN; // 如果没有特殊规则，返回 Abstain
//        }
//        String url = (String) targetUrl;
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String httpMethod = request.getMethod();
//
//        System.out.println("httpMethod = " + httpMethod);
//        System.out.println("url = " + url);


        if (object instanceof FilterInvocation) {
            FilterInvocation filterInvocation = (FilterInvocation) object;
            HttpServletRequest request = filterInvocation.getRequest();
            String requestUrl = request.getRequestURI();

            // 你可以根据请求 URL 做进一步的权限判断
            System.out.println("Requested URL: " + requestUrl);

            if (requestUrl.equals("/login") || requestUrl.equals("/favicon.ico")) {
                return ACCESS_GRANTED;
            }
        }


        // 示例：根据 URL 判断权限
//        if (requestUrl.equals("/admin")) {
//            return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ? ACCESS_GRANTED : ACCESS_DENIED;
//        } else if (requestUrl.equals("/user")) {
//            return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")) ? ACCESS_GRANTED : ACCESS_DENIED;
//        }
        return ACCESS_DENIED; // 权限不足
//        return ACCESS_ABSTAIN; // 权限不足
//        return ACCESS_GRANTED; // 权限不足


    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true; // 返回true表示支持所有类型的ConfigAttribute
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true; // 返回true表示支持所有类型的对象
    }
}
