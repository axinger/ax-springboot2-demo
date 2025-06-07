package com.github.axinger.server;

import com.github.axinger.api.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@GrpcService
@Slf4j
public class StockServiceImpl extends StockServiceGrpc.StockServiceImplBase {

    private static final Map<String, StreamObserver<StockResponse>> activeClients = new ConcurrentHashMap<>();

    @Override
    public StreamObserver<StockRequest> stock(StreamObserver<StockResponse> responseObserver) {

        return new StreamObserver<>() {
            private String userId;

            @Override
            public void onNext(StockRequest request) {
                if (request.hasHeartbeat()) {
                    // 处理心跳
                    responseObserver.onNext(StockResponse.newBuilder()
                            .setHeartbeat(StockHeartbeat.newBuilder()
                                    .setTimestamp(System.currentTimeMillis())
                                    .build())
                            .build());
                    log.info("收到心跳: {}", request.getHeartbeat().getTimestamp());
                    return;
                }
                userId = request.getUserId();
                // 添加前检查是否已存在
                StreamObserver<StockResponse> previous = activeClients.put(userId, responseObserver);
                if (previous != null) {
                    // 处理旧连接
                    try {
                        previous.onError(Status.ALREADY_EXISTS.asRuntimeException());
                    } catch (Exception e) {
                        log.warn("关闭旧连接失败", e);
                    }
                }
                log.info("客户端 {} 注册成功", userId);
            }

            @Override
            public void onError(Throwable t) {
                log.error("客户端 {} 连接错误: {}", userId, t.getMessage());
                cleanup();
            }

            @Override
            public void onCompleted() {
                log.info("客户端 {} 连接正常关闭", userId);
                cleanup();
            }

            private void cleanup() {
                if (userId != null) {
                    StreamObserver<StockResponse> observer = activeClients.remove(userId);
                    if (observer == responseObserver) {
                        log.info("移除客户端 {}", userId);
                    }
                }
            }
        };
    }

    public boolean pushToClient(String userId, String message) {
        StreamObserver<StockResponse> responseObserver = activeClients.get(userId);
        if (responseObserver != null) {
            synchronized (responseObserver) {
                try {
                    StockResponse response = StockResponse.newBuilder().setUserId(userId).setMessage(message).build();
                    responseObserver.onNext(response);
                    return true;
                } catch (Exception e) {
                    log.error("推送消息到客户端 {} 失败，移除连接", userId, e);
                    activeClients.remove(userId);
                    return false;
                }
            }
        } else {
            log.warn("客户端 {} 不存在或已断开", userId);
            return false;
        }
    }

}
