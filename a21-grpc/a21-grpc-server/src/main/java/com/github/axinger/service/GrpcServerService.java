package com.github.axinger.service;

import com.github.axinger.api.MyRequest;
import com.github.axinger.api.MyResponse;
import com.github.axinger.api.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;


/**
 * GrpcServerService.java中有几处需要注意：
 * <p>
 * 是使用@GrpcService注解，再继承SimpleImplBase，这样就可以借助grpc-server-spring-boot-starter库将oneToOne暴露为gRPC服务；
 * <p>
 * SimpleImplBase是前文中根据maven compile编译 proto文件自动生成的java代码，
 * <p>
 * oneToOne方法中处理完毕业务逻辑后，调用responseObserver.onNext方法填入返回内容；
 * <p>
 * 调用responseObserver.onCompleted方法表示本次gRPC服务完成；
 */
@GrpcService
@Slf4j
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {

    @Override
    public void oneToOne(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        log.info("接收客户端数据{}", request);
        MyResponse response = MyResponse.newBuilder()
                .setMessage("接收到参数,返回" + request.getName())
                .setResult(1)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
