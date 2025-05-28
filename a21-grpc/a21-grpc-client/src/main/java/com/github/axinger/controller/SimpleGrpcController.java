package com.github.axinger.controller;

import com.github.axinger.api.MyRequest;
import com.github.axinger.api.MyResponse;
import com.github.axinger.api.SimpleGrpc;
import com.github.axinger.service.GrpcClientService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleGrpcController {
    @Autowired
    private GrpcClientService service;

    @GetMapping("/test1")
    public Object test1() {
        try {
            System.out.println("test1==========");
            return service.test1("客户端kele连接");
        } catch (Exception e) {
            System.err.println("test1 error=" + e.getMessage());
            return null;
        }
    }

    @GetMapping("/test2")
    public Object test2() {
        return service.test3();
    }

    @GetMapping("/test3")
    public Object test3() {
        ManagedChannel channel = ManagedChannelBuilder
/// /                .forAddress("localhost", 19898)
                .forTarget("a21-grpc-server")
                .usePlaintext()
                .build();
        MyRequest request = MyRequest.newBuilder().setName("jim").build();
        SimpleGrpc.SimpleBlockingStub stub = SimpleGrpc.newBlockingStub(channel);
        MyResponse myResponse = stub.oneToOne(request);
        String message = myResponse.getMessage();
        System.out.println("message = " + message);

        return message;
    }


}
