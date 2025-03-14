//package com.github.axinger.service;
//
//import com.github.axinger.api.Hello;
//import com.github.axinger.api.HelloServiceGrpc;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class HelloClient {
//    private final HelloServiceGrpc.HelloServiceBlockingStub blockingStub;
//    private final HelloServiceGrpc.HelloServiceFutureStub futureStub;
//    private final HelloServiceGrpc.HelloServiceStub asyncStub;
//
//    public HelloClient(ManagedChannel channel) {
//        this.blockingStub = HelloServiceGrpc.newBlockingStub(channel);
//        this.futureStub = HelloServiceGrpc.newFutureStub(channel);
//        this.asyncStub = HelloServiceGrpc.newStub(channel);
//    }
//
//    // 同步调用
//    public Hello.HelloResponse sayHelloSync(String name) {
//        return blockingStub.sayHello(Hello.HelloRequest.newBuilder().setName(name).build());
//    }
//
//    // 异步调用（Future）
//    public void sayHelloAsync(String name) {
//        futureStub.sayHello(Hello.HelloRequest.newBuilder().setName(name).build())
//            .whenComplete((response, throwable) -> {
//                if (throwable != null) {
//                    System.out.println("Error: " + throwable.getMessage());
//                } else {
//                    System.out.println("Response: " + response.getMessage());
//                }
//            });
//    }
//
//    // 服务端流式调用
//    public void streamingHello(String name) {
//        asyncStub.streamingHello(Hello.HelloRequest.newBuilder().setName(name).build())
//            .onNext(response -> System.out.println("Streaming: " + response.getMessage()))
//            .onCompleted(() -> System.out.println("Streaming completed"))
//            . onError(throwable -> System.err.println("Error: " + throwable));
//    }
//}
