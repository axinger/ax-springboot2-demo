package com.github.axinger;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveWebSocketServerHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
//        Mono<Void> send = session.send(Flux.create(sink -> {
//            //可以持有sink对象在任意时候调用next发送消息
////            sink.next( );
//        })).doOnError(it -> {
//            //异常处理
//        });
//
//        Mono<Void> receive = session.receive()
//                .doOnNext(it -> {
//                    //接收消息
//                })
//                .doOnError(it -> {
//                    //异常处理
//                })
//                .then();
//
//        Disposable disposable = session.closeStatus()
//                .doOnError(it -> {
//                    //异常处理
//                })
//                .subscribe(it -> {
//                    //连接关闭
//                });
//
//        return Mono.zip(send, receive).then();


        Flux<WebSocketMessage> messageFlux = session.receive().map(message -> {
            String payload = message.getPayloadAsText();
            return "Received: " + payload;
        }).map(session::textMessage);
        return session.send(messageFlux);
    }
}
