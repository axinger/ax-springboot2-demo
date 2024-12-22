package com.github.axinger.service;

import com.github.axinger.api.MyRequest;
import com.github.axinger.api.MyResponse;
import com.github.axinger.api.SimpleGrpc;
import io.grpc.StatusRuntimeException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @GrpcClient("a21-grpc-server")
    private SimpleGrpc.SimpleBlockingStub simpleStub;

    public Map<String, Object> oneToOne(final String name) {
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

}
