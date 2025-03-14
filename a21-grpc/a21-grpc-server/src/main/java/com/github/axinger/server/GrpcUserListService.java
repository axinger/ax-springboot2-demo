//package com.github.axinger.server;
//
//import com.github.axinger.api.ResponseOuterClass.Response;
//import com.github.axinger.api.ResponsePayloadOuterClass.ResponsePayload;
//import com.github.axinger.api.StatusCodeOuterClass.StatusCode;
//import com.github.axinger.api.UserOuterClass;
//import com.github.axinger.api.UserOuterClass.User;
//import com.github.axinger.api.UserOuterClass.UserList;
//import com.github.axinger.api.UserServiceGrpc;
//import io.grpc.stub.StreamObserver;
//import lombok.extern.slf4j.Slf4j;
//import net.devh.boot.grpc.server.service.GrpcService;
//
//import java.util.List;
//
//
//@GrpcService
//@Slf4j
//public class GrpcUserListService extends UserServiceGrpc.UserServiceImplBase {
//    @Override
//    public void getUserList(UserList request, StreamObserver<Response> responseObserver) {
//
//
//        List<UserOuterClass.User> usersList = request.getDataList();
//        System.out.println("请求参数usersList = " + usersList);
//
//        List<User> list = usersList.stream()
//                .map(val -> User.newBuilder().setId(val.getId()).setName(val.getName() + "A").build())
//                .toList();
//
//        UserList userList = UserList.newBuilder().addAllData(list).build();
//
//        ///  这个也可以直接传值
////        UserList.Builder builder = UserList.newBuilder().addAllUsers(usersList);
//
//        ResponsePayload payload = ResponsePayload.newBuilder()
//                .setUserList(userList)
//                .build();
//
//        Response response = Response.newBuilder()
//                .setCode(StatusCode.SUCCESS)
//                .setMessage("success")
//                .setPayload(payload)
//                .build();
//
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//
//    }
//}
