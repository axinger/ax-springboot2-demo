package com.github.axinger.config;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.axinger.api.StockRequest;
import com.github.axinger.api.StockResponse;
import com.github.axinger.api.StockServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Setter
@Slf4j
@Component
@Order(1)
@RestController
public class StockService {

    @GrpcClient("a21-grpc-server")
    private StockServiceGrpc.StockServiceStub stockServiceStub;
    private volatile StreamObserver<StockRequest> requestObserver;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        connection();
    }

    private void connection() {
        log.info("尝试建立新连接...");

        try {
            StreamObserver<StockResponse> responseObserver = new StreamObserver<>() {
                @Override
                public void onNext(StockResponse response) {
                    if (response.hasHeartbeat()) {
                        log.debug("收到心跳响应");
                    } else {
                        log.info("收到服务器消息: {}", response.getMessage());
                    }
                }

                @Override
                public void onError(Throwable t) {
                    log.error("服务器连接错误={}", ExceptionUtil.getRootCauseMessage(t), t);
                }

                @Override
                public void onCompleted() {
                    log.info("服务器关闭了连接");
                }
            };

            requestObserver = stockServiceStub.stock(responseObserver);
            StockRequest request = StockRequest.newBuilder()
                    .setUserId("jim")
                    .setMessage("我要注册...")
                    .build();
            requestObserver.onNext(request);
            log.info("连接建立成功");
        } catch (Exception e) {
            log.error("建立连接失败", e);
        }
    }

    public boolean sendMessage(String message) {
        if (requestObserver == null) {
            return false;
        }
        try {
            StockRequest request = StockRequest.newBuilder()
                    .setUserId("jim")
                    .setMessage(message)
                    .build();
            synchronized (this) {
                if (requestObserver != null) {
                    requestObserver.onNext(request);
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("发送消息失败", e);
        }
        return false;
    }


}
