package com.github.axinger.controller;

import com.github.axinger.api.MyRequest;
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

    @GetMapping("test1")
    public Object getOneToOne() {
        return service.oneToOne("客户端kele连接");
    }


    @GetMapping("testForAddress")
    public Object testForAddress() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 19898)
                .usePlaintext()
                .build();
        MyRequest request = MyRequest.newBuilder().setName("kele的访问").build();
        SimpleGrpc.SimpleBlockingStub stub = SimpleGrpc.newBlockingStub(channel);
        return stub.oneToOne(request);
    }


    @GetMapping("test2")
    public Object test3() {
        return service.test3();
    }
}
