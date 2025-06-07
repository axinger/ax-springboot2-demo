package com.github.axinger.server;

import com.github.axinger.api.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;


@GrpcService
@Slf4j
public class UserServiceGrpcUserService extends UserServiceGrpc.UserServiceImplBase {


    @Override
    public void getUserList(UserOuterClass.UserList request, StreamObserver<ResponseOuterClass.Response> responseObserver) {


        List<UserOuterClass.User> usersList = request.getDataList();
        System.out.println("请求参数usersList = " + usersList);

        List<UserOuterClass.User> list = usersList.stream()
                .map(val -> UserOuterClass.User.newBuilder().setId(val.getId()).setName(val.getName() + "A").build())
                .toList();

        UserOuterClass.UserList userList = UserOuterClass.UserList.newBuilder().addAllData(list).build();

        ///  这个也可以直接传值
        /// /        UserList.Builder builder = UserList.newBuilder().addAllUsers(usersList);

        ResponsePayloadOuterClass.ResponsePayload payload = ResponsePayloadOuterClass.ResponsePayload.newBuilder()
                .setUserList(userList)
                .build();

        ResponseOuterClass.Response response = ResponseOuterClass.Response.newBuilder()
                .setCode(StatusCodeOuterClass.StatusCode.SUCCESS)
                .setMessage("success")
                .setPayload(payload)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
