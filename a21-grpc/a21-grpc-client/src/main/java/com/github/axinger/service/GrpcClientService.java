package com.github.axinger.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.github.axinger.api.*;
import com.github.axinger.api.ResponseOuterClass.Response;
import com.github.axinger.api.ResponsePayloadOuterClass.ResponsePayload;
import com.github.axinger.api.StatusCodeOuterClass.StatusCode;
import com.github.axinger.api.UserOuterClass.User;
import com.github.axinger.api.UserOuterClass.UserList;
import com.google.common.base.Throwables;
import com.google.protobuf.StringValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;
import io.grpc.StatusRuntimeException;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GrpcClientService类有几处要注意的地方：
 * <p>
 * 用@Service将GrpcClientService注册为spring的普通bean实例；
 * <p>
 * 用@GrpcClient修饰SimpleBlockingStub，这样就可以通过grpc-client-spring-boot-starter库发起gRPC调用，被调用的服务端信息来自名为nacos-grpc服务端配置；
 * <p>
 * SimpleBlockingStub来自前文中根据helloworld.proto生成的java代码；
 * <p>
 * SimpleBlockingStub.oneToOne方法会远程调用nacos-grpc应用的gRPC服务；
 */
@Setter
@Service
@Slf4j
public class GrpcClientService {

    // 方法名称Grpc.方法名称BlockingStub
    @GrpcClient("a21-grpc-server")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    @GrpcClient("a21-grpc-server")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    public Map<String, Object> test1(final String name) {
        try {
            final MyResponse response = simpleStub.oneToOne(MyRequest.newBuilder().setName(name).build());

            Map<String, Object> map = new HashMap<>();

            map.put("message", response.getMessage());
            map.put("result", response.getResult());
            return map;
        } catch (final StatusRuntimeException e) {
            log.error("FAILED with={} ", e.getMessage());
            throw e;
        }
    }

    @SneakyThrows
    public Map<String, Object> test3() {
        // 构建请求
        List<User> list = new ArrayList<>() {{
            add(User.newBuilder().setName("张三").build());
            add(User.newBuilder().setName("李四").build());
        }};

        UserList userList = UserList.newBuilder().addAllData(list).build();

        // 发送请求并接收响应
        Response response = userServiceBlockingStub.getUserList(userList);

        // 处理响应
        Map<String, Object> map = new HashMap<>();
        StatusCode code = response.getCode();
        String message = response.getMessage();
        ResponsePayload payload = response.getPayload();

        // 检查 payload 类型，并安全地获取 UserList
        if (response.hasPayload() && payload.hasUserList()) {
            UserList userList1 = payload.getUserList();
            ///  这个不能直接返回前端,无法序列化
            List<User> usersList = userList1.getDataList();

            String jsonString = JsonFormat.printer().print(userList);

            // 如果需要进一步处理 JSON 字符串，可以使用 Jackson ObjectMapper

            Map<String, Object> jsonMap = JSON.parseObject(jsonString, new TypeReference<>() {
            });
            System.out.println("jsonMap = " + jsonMap);


            map.put("code", code);
            map.put("message", message);
            map.putAll(jsonMap);
        } else {
            // 如果 payload 不是预期的类型，则处理错误情况
            map.put("code", code);
            map.put("message", message);
            map.put("error", "Unexpected payload type or empty payload");
        }
        return map;
    }

    @SneakyThrows
    public void test4() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jim");
        String jsonString = JSON.toJSONString(map);
        StringValue hello = StringValue.newBuilder().setValue(jsonString).build();
        StringValue stub1 = simpleStub.get1(hello);
        String value = stub1.getValue();
        System.out.println(value);
    }


    @SneakyThrows
    public void test5() {
        StringValue hello = StringValue.newBuilder().setValue("hello").build();
        MyStructDto stub2 = simpleStub.get2(hello);

        Struct details = stub2.getDetails();

        {
            Value name = details.getFieldsOrThrow("name");
            String stringValue = name.getStringValue();
            System.out.println("stringValue = " + stringValue);
        }

        try {
            Value name = details.getFieldsOrThrow("name2");
            String stringValue = name.getStringValue();
            System.out.println("stringValue = " + stringValue);
        } catch (Exception e) {
            log.info("错误={}", Throwables.getRootCause(e).getMessage());
        }

        {

            Value name2 = details.getFieldsOrDefault("name1", Value.newBuilder().setStringValue("没有取到默认值").build());
            String stringValue1 = name2.getStringValue();
            System.out.println("stringValue1 = " + stringValue1);
        }


    }
}
