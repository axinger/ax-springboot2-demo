package com.github.axinger.gateway.config;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Order(-2)
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    private final List<ViewResolver> viewResolvers = Collections.emptyList();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "服务异常";

        // 1. 解析异常类型
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseEx = (ResponseStatusException) ex;
            status = (HttpStatus) responseEx.getStatus();
            message = responseEx.getReason() != null ? responseEx.getReason() : status.getReasonPhrase();
        }

        // 2. 构建统一响应体
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", status.value());
        responseBody.put("message", message);
        responseBody.put("path", exchange.getRequest().getPath().toString());
        responseBody.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));

        // 3. 写入 JSON 响应
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);


//        return ServerResponse.status(status)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(rs))
//                .flatMap(res -> res.writeTo(exchange, new ServerResponse.Context() {
//                    @Override
//                    public List<HttpMessageWriter<?>> messageWriters() {
//                        return GlobalExceptionHandler.this.messageWriters;
//                    }
//
//                    @Override
//                    public List<ViewResolver> viewResolvers() {
//                        return GlobalExceptionHandler.this.viewResolvers;
//                    }
//                }));


        try {
            byte[] bytes = objectMapper.writeValueAsBytes(responseBody);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return Mono.error(e);
        }
    }
}
