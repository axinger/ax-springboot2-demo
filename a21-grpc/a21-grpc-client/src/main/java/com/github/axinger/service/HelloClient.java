package com.github.axinger.service;

import com.github.axinger.api.Hello;
import com.github.axinger.api.HelloServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Setter
@Service
@Slf4j
public class HelloClient {

    @GrpcClient("a21-grpc-server")
    private HelloServiceGrpc.HelloServiceBlockingStub blockingStub;

    @GrpcClient("a21-grpc-server")
    private HelloServiceGrpc.HelloServiceFutureStub futureStub;

    @GrpcClient("a21-grpc-server")
    private HelloServiceGrpc.HelloServiceStub asyncStub;


    @Autowired
    private Executor executor;


    // 同步调用
    public Hello.HelloResponse sayHelloSync(String name) {
        Hello.HelloResponse helloResponse = blockingStub.sayHello(Hello.HelloRequest.newBuilder().setName(name).build());
        System.out.println("helloResponse = " + helloResponse);

        return helloResponse;
    }

    // 异步调用（Future）
    public void sayHelloAsync(String name) {
        ListenableFuture<Hello.HelloResponse> helloResponseListenableFuture = futureStub.sayHello(Hello.HelloRequest.newBuilder().setName(name).build());
        helloResponseListenableFuture.addListener(() -> {

        }, executor);

//            .whenComplete((response, throwable) -> {
//                if (throwable != null) {
//                    System.out.println("Error: " + throwable.getMessage());
//                } else {
//                    System.out.println("Response: " + response.getMessage());
//                }
//            });
    }

    //
    // 服务端流式调用
    public void streamingHello(String name) {
        io.grpc.stub.StreamObserver<Hello.HelloResponse> responseObserver = new io.grpc.stub.StreamObserver<Hello.HelloResponse>() {

            @Override
            public void onNext(Hello.HelloResponse helloResponse) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
        asyncStub.streamingHello(Hello.HelloRequest.newBuilder().setName(name).build(), responseObserver);
//            .onNext(response -> System.out.println("Streaming: " + response.getMessage()))
//            .onCompleted(() -> System.out.println("Streaming completed"))
//            . onError(throwable -> System.err.println("Error: " + throwable));
    }
}
