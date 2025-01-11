package com.github.axinger.gateway.config;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.reactive.CorsWebFilter;
// import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
// import org.springframework.web.util.pattern.PathPatternParser;
//


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

@Configuration
@Slf4j
public class GatewayConfig {

    @Autowired
    private ObjectMapper objectMapper;

//    @Bean
//    public CorsWebFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedMethod("*");//支持所有方法
//        config.addAllowedOrigin("*");//跨域处理 允许所有的域
//        config.addAllowedHeader("*");//支持所有请求头
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", config);//匹配所有请求
//        return new CorsWebFilter(source);
//    }


    @Bean
    @Order()
    ///  @Order 注解中的值越小，优先级越高
    public GlobalFilter globalFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            // 提取request请求内容
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().pathWithinApplication().value();
            String scheme = request.getURI().getScheme();
            HttpMethod method = request.getMethod();
            HttpHeaders httpHeaders = request.getHeaders();
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            // 不建议提取body数据，因为请求体数据只能被消费一次。
            log.info("\npath:{}\nscheme:{}\nmethod:{}\nheaders:{}\nremoteAddress:{}", path, scheme, method, httpHeaders, remoteAddress);
            // header增加token
            ServerHttpRequest newRequest = exchange.getRequest().mutate().headers(headers -> {
                headers.add("x-fetch-gateway-token", "ABC123");
            }).build();

            // 计算请求时长
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            return chain.filter(exchange.mutate().request(newRequest).build()).then(Mono.fromRunnable(() -> {
                stopWatch.stop();
                log.info("请求={}m耗时={}", exchange.getRequest().getURI().getRawPath(), stopWatch.getTotalTimeSeconds());
            }));
        };

    }

    @Bean
    public ErrorWebExceptionHandler errorWebExceptionHandler() {
        return (ServerWebExchange exchange, Throwable ex) -> {
            ServerHttpResponse response = exchange.getResponse();
            if (response.isCommitted()) {
                return Mono.error(ex);
            }
            // header set
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            if (ex instanceof ResponseStatusException exception) {
//            response.setStatusCode(exception.getStatus());
//            response.setStatusCode(HttpStatusCode.valueOf(201));
                response.setStatusCode(HttpStatus.CREATED);
            }
            return response
                    .writeWith(Mono.fromSupplier(() -> {
                        DataBufferFactory bufferFactory = response.bufferFactory();
                        try {
                            return bufferFactory.wrap(objectMapper.writeValueAsBytes(ex.getMessage()));
                        } catch (JsonProcessingException e) {
                            log.warn("Error writing response", ex);
                            return bufferFactory.wrap(new byte[0]);
                        }
                    }));
        };
    }
}