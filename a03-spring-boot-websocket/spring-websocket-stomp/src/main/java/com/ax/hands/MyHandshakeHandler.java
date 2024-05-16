package com.ax.hands;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName MyHandshakeHandler.java
 * @description TODO
 * @createTime 2022年06月11日 21:58:00
 */
@Component
@Slf4j
public class MyHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      @NonNull WebSocketHandler wsHandler,
                                      @NonNull Map<String, Object> attributes) {


        // 中的最后一个用户 id 参数作为用户的标识,
        // 为实现发送信息给指定用户做准备
        String uri = request.getURI().toString();
        String uid = uri.substring(uri.lastIndexOf("/") + 1);
        return () -> uid;
    }
}
