package com.ax.hands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName MyHandshakeInterceptor.java
 * @description TODO
 * @createTime 2022年06月11日 16:42:00
 */
@Component
@Slf4j
public class MyHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("beforeHandshake attributes = {}", attributes);

        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

        String id = servletRequest.getSession().getId();
        System.out.println("id = " + id);


        URI url = request.getURI();

        System.out.println("url = " + url);

        String type = servletRequest.getParameter("id");
        System.out.println("type = " + type);

        String name = servletRequest.getParameter("name");
        System.out.println("name = " + name);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("afterHandshake = ");
    }
}
