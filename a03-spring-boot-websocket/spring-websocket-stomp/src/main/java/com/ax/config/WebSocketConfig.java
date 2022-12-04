package com.ax.config;

import com.ax.hands.MyHandshakeHandler;
import com.ax.hands.MyHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * @Author JCccc
 * @Description EnableWebSocketMessageBroker-注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用@MessageMapping
 * @Date 2021/6/30 8:53
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    MyHandshakeInterceptor myHandshakeInterceptor;


    @Autowired
    MyHandshakeHandler myHandshakeHandler;

    /**
     * 开放节点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册两个STOMP的endpoint，分别用于广播和点对点

        /**
         *     SockJS 是 WebSocket 技术的一种模拟。为了应对许多浏览器不支持WebSocket协议的问题，设计了备选SockJs。
         *     开启并使用SockJS后，它会优先选用Websocket协议作为传输协议，
         *     如果浏览器不支持Websocket协议，则会在其他方案中，选择一个较好的协议进行通讯。
         */

        // 广播
        registry.addEndpoint("/publicServer")
                .addInterceptors(myHandshakeInterceptor)
//                .setHandshakeHandler(myHandshakeHandler)
                .setAllowedOriginPatterns("*")
                // postman ws://localhost:9908/publicServer/jim/a/websocket
                // 最多2段,必须 websocket结尾
                // 最后可以加上"/websocket"强制使用websocket协议
                .withSockJS()
        ;


        // 点对点
        registry.addEndpoint("/privateServer")
                .addInterceptors(myHandshakeInterceptor)
//                .setHandshakeHandler(myHandshakeHandler)
                .setAllowedOriginPatterns("*")
                .withSockJS()
        ;
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // topic用来广播，user用来实现点对点
        config.enableSimpleBroker("/topic", "/user");

        // 一对一发送前缀
//        config.setUserDestinationPrefix("/user");
//
//        //后台应用接收浏览器消息端点前缀，这个将直接路由到@MessageMapping上
//        config.setApplicationDestinationPrefixes("/ws");
    }

}
