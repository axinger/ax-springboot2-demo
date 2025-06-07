package com.github.axinger.server;

import com.alibaba.fastjson2.JSON;
import com.github.axinger.api.*;
import com.google.protobuf.ListValue;
import com.google.protobuf.StringValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/// **
// * GrpcServerService.java中有几处需要注意：
// * <p>
// * 是使用@GrpcService注解，再继承SimpleImplBase，这样就可以借助grpc-server-spring-boot-starter库将oneToOne暴露为gRPC服务；
// * <p>
// * SimpleImplBase是前文中根据maven compile编译 proto文件自动生成的java代码，
// * <p>
// * oneToOne方法中处理完毕业务逻辑后，调用responseObserver.onNext方法填入返回内容；
// * <p>
// * 调用responseObserver.onCompleted方法表示本次gRPC服务完成；
// */
@GrpcService
@Slf4j
public class SimpleGrpcSimpleService extends SimpleGrpc.SimpleImplBase {

    private static final Map<String, StreamObserver<MyResponse>> activeClients = new ConcurrentHashMap<>();

    @Override
    public void sendMessage(MyRequest request, StreamObserver<MyResponse> responseObserver) {

        log.info("sendMessage={}", request.getName());
        MyResponse response = MyResponse.newBuilder()
                .setMessage("SimpleGrpc.SimpleImplBase#sendMessage,返回" + request.getName())
                .setResult(1)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageStream(MyRequest request, StreamObserver<MyResponse> responseObserver) {

        log.info("开始流式处理请求: {}", request.getName());

        try {
            // 示例：发送3个流式响应
            for (int i = 1; i <= 3; i++) {
                MyResponse response = MyResponse.newBuilder()
                        .setMessage(request.getName() + "###" + i)
                        .setResult(i)
                        .build();
                responseObserver.onNext(response); // 发送单个响应
                Thread.sleep(1000); // 模拟处理延迟
            }
        } catch (Exception e) {
            responseObserver.onError(e); // 异常处理
        } finally {
            responseObserver.onCompleted();
            log.info("流式处理完成");
        }
    }

    @Override
    public void get1(StringValue request, StreamObserver<StringValue> responseObserver) {


        String value = request.getValue();
        System.out.println("get1参数 = " + value);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "jim");
        map.put("age", 10);

        String jsonString = JSON.toJSONString(map);
        StringValue stringValue = StringValue.of(jsonString);
        responseObserver.onNext(stringValue);
        responseObserver.onCompleted();
    }

    @SneakyThrows
    @Override
    public void get2(StringValue request, StreamObserver<MyStructDto> responseObserver) {
        log.info("get2接收客户端数据{}", request);

        // 创建 Struct 并填充数据
        Struct.Builder structBuilder = Struct.newBuilder();

        // 添加一个字符串字段
        structBuilder.putFields("name", Value.newBuilder().setStringValue("Alice").build());

        // 添加一个数字字段
        structBuilder.putFields("age", Value.newBuilder().setNumberValue(30).build());

        // 创建一个嵌套的对象（地址）
        Struct addressStruct = Struct.newBuilder()
                .putFields("street", Value.newBuilder().setStringValue("123 Main St").build())
                .putFields("city", Value.newBuilder().setStringValue("Wonderland").build())
                .build();
        structBuilder.putFields("address", Value.newBuilder().setStructValue(addressStruct).build());

        // 创建一个数组（爱好）
        ListValue hobbiesList = ListValue.newBuilder()
                .addValues(Value.newBuilder().setStringValue("Reading").build())
                .addValues(Value.newBuilder().setStringValue("Hiking").build())
                .build();
        structBuilder.putFields("hobbies", Value.newBuilder().setListValue(hobbiesList).build());

        Map<String, Value> map = new HashMap<>();

        structBuilder.putAllFields(map);

        // 构建最终的 Struct
        Struct details = structBuilder.build();


        // 创建请求对象并设置 details 字段
        MyStructDto dto = MyStructDto.newBuilder()
                .setDetails(details)
                .build();

        // 打印出 JSON 格式的请求内容以验证
//        System.out.println(JsonFormat.printer().print(dto.getDetails()));

        responseObserver.onNext(dto);
        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<MyRequest> serverPushStream(StreamObserver<MyResponse> responseObserver) {
        return new StreamObserver<>() {
            private String clientId;

            @Override
            public void onNext(MyRequest request) {
                if (request.hasHeartbeat()) {
                    // 处理心跳
                    responseObserver.onNext(MyResponse.newBuilder()
                            .setHeartbeat(Heartbeat.newBuilder()
                                    .setTimestamp(System.currentTimeMillis())
                                    .build())
                            .build());
                    log.info("收到心跳: {}", request.getHeartbeat().getTimestamp());
                    return;
                }
                clientId = request.getName();
                // 添加前检查是否已存在
                StreamObserver<MyResponse> previous = activeClients.put(clientId, responseObserver);
                if (previous != null) {
                    // 处理旧连接
                    try {
                        previous.onError(Status.ALREADY_EXISTS.asRuntimeException());
                    } catch (Exception e) {
                        log.warn("关闭旧连接失败", e);
                    }
                }
                log.info("客户端 {} 注册成功", clientId);
            }

            @Override
            public void onError(Throwable t) {
                log.error("客户端 {} 连接错误: {}", clientId, t.getMessage());
                cleanup();
            }

            @Override
            public void onCompleted() {
                log.info("客户端 {} 连接正常关闭", clientId);
                cleanup();
            }

            private void cleanup() {
                if (clientId != null) {
                    StreamObserver<MyResponse> observer = activeClients.remove(clientId);
                    if (observer == responseObserver) {
                        log.info("移除客户端 {}", clientId);
                    }
                }
            }
        };
    }

    public boolean pushToClient(String clientId, String message) {
        StreamObserver<MyResponse> observer = activeClients.get(clientId);
        if (observer != null) {
            synchronized (observer) {
                try {
                    observer.onNext(MyResponse.newBuilder().setResult(1).setMessage(message).build());
                    return true;
                } catch (Exception e) {
                    log.error("推送消息到客户端 {} 失败，移除连接", clientId, e);
                    activeClients.remove(clientId);
                    return false;
                }
            }
        } else {
            log.warn("客户端 {} 不存在或已断开", clientId);
            return false;
        }
    }


}
