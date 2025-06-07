package com.github.axinger.event;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.github.axinger.api.Heartbeat;
import com.github.axinger.api.MyRequest;
import com.github.axinger.api.MyResponse;
import com.github.axinger.api.SimpleGrpc;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Setter
@Slf4j
@Component
@Order(1)
public class FromServletService {

    private final AtomicBoolean running = new AtomicBoolean(true);
    private final ExecutorService connectionExecutor = Executors.newSingleThreadExecutor();
    private ScheduledExecutorService heartbeatExecutor = Executors.newSingleThreadScheduledExecutor();
    @GrpcClient("a21-grpc-server")
    private SimpleGrpc.SimpleStub simpleStub;
    private volatile StreamObserver<MyRequest> requestObserver;
    private volatile ManagedChannel managedChannel;

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        connectionExecutor.execute(this::manageConnection);
    }

    private void manageConnection() {
        while (running.get() && !Thread.currentThread().isInterrupted()) {
            try {
                cleanupConnection();
                establishConnection();

                // 保持连接活跃检查
                while (isConnectionActive() && running.get()) {
                    TimeUnit.SECONDS.sleep(1);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                log.error("连接异常", e);
            }

            if (running.get()) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    private void establishConnection() {
        log.info("尝试建立新连接...");

        try {
            // 获取ManagedChannel
            managedChannel = (ManagedChannel) simpleStub.getChannel();

            StreamObserver<MyResponse> responseObserver = new StreamObserver<MyResponse>() {
                @Override
                public void onNext(MyResponse response) {
                    if (response.hasHeartbeat()) {
                        log.debug("收到心跳响应");
                    } else {
                        log.info("收到服务器消息: {}", response.getMessage());
                    }
                }

                @Override
                public void onError(Throwable t) {
                    log.error("服务器连接错误={}", ExceptionUtil.getRootCauseMessage(t), t);
                    scheduleReconnect();
                }

                @Override
                public void onCompleted() {
                    log.info("服务器关闭了连接");
                    scheduleReconnect();
                }
            };

            requestObserver = simpleStub.serverPushStream(responseObserver);
            sendRegistration();
            startHeartbeat();

            log.info("连接建立成功");
        } catch (Exception e) {
            log.error("建立连接失败", e);
            scheduleReconnect();
        }
    }

    private void sendRegistration() {
        MyRequest request = MyRequest.newBuilder()
                .setName("a21-grpc-client")
                .build();

        synchronized (this) {
            if (requestObserver != null) {
                requestObserver.onNext(request);
            }
        }
    }

    private void startHeartbeat() {
        heartbeatExecutor.scheduleAtFixedRate(() -> {
            if (!running.get() || !isConnectionActive()) {
                return;
            }

            try {
                MyRequest heartbeat = MyRequest.newBuilder()
                        .setName("a21-grpc-client")
                        .setHeartbeat(Heartbeat.newBuilder()
                                .setTimestamp(System.currentTimeMillis())
                                .build())
                        .build();

                synchronized (this) {
                    if (requestObserver != null) {
                        requestObserver.onNext(heartbeat);
                        log.info("心跳发送成功");
                    }
                }
            } catch (Exception e) {
                log.error("发送心跳失败", e);
                scheduleReconnect();
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

    private void cleanupConnection() {
        log.info("清理现有连接...");

        // 停止心跳
        if (heartbeatExecutor != null) {
            heartbeatExecutor.shutdownNow();
            try {
                if (!heartbeatExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                    heartbeatExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // 重建 executor
        heartbeatExecutor = Executors.newSingleThreadScheduledExecutor();

        // 关闭请求流
        synchronized (this) {
            if (requestObserver != null) {
                try {
                    requestObserver.onCompleted();
                } catch (Exception e) {
                    log.warn("关闭请求流时出错", e);
                }
                requestObserver = null;
            }
        }

        // 关闭通道
        if (managedChannel != null && !managedChannel.isShutdown()) {
            try {
                managedChannel.shutdownNow();
            } catch (Exception e) {
                log.warn("关闭通道时出错", e);
            }
            managedChannel = null;
        }
    }

    private boolean isConnectionActive() {
        return managedChannel != null && !managedChannel.isShutdown() && !managedChannel.isTerminated();
    }

    private void scheduleReconnect() {
        if (running.get()) {
            log.info("安排重新连接...");
            connectionExecutor.execute(this::manageConnection);
        }
    }

    public boolean sendMessage(String message) {
        if (!isConnectionActive() || requestObserver == null) {
            return false;
        }

        try {
            MyRequest request = MyRequest.newBuilder()
                    .setName("a21-grpc-client")
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

    @PreDestroy
    public void shutdown() {
        log.info("关闭gRPC客户端...");
        running.set(false);

        connectionExecutor.shutdownNow();
        cleanupConnection();
        heartbeatExecutor.shutdownNow();

        try {
            if (!connectionExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                connectionExecutor.shutdownNow();
            }
            if (!heartbeatExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                heartbeatExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("gRPC客户端已关闭");
    }
}
