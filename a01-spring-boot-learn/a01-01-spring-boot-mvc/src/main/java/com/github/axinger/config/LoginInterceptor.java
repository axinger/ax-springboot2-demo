package com.github.axinger.config;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws ServletException, IOException {
        System.out.println("LoginInterceptor===================");
        Object user = request.getSession().getAttribute("user");
//        if (user == null) {
//            request.setAttribute("msg", "没有权限");
//            request.getRequestDispatcher("/index").forward(request, response);
//            return false;
//        } else {
//            return true;
//        }

        return true;
    }
}
