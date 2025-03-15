package com.github.axinger.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RefreshScope
@Order(-1)
public class AuthFilter implements GlobalFilter {

    @Value("${whitelist.paths}")
    List<String> whitelistPaths;


    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static Map<String, Object> getRequesttMap(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().pathWithinApplication().value();
        String scheme = request.getURI().getScheme();
        HttpMethod method = request.getMethod();
        HttpHeaders httpHeaders = request.getHeaders();
        InetSocketAddress remoteAddress = request.getRemoteAddress();

        // 不建议提取body数据，因为请求体数据只能被消费一次。
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("scheme", scheme);
        attributes.put("method", method);
        attributes.put("remoteAddress", remoteAddress);
        attributes.put("path", path);
        attributes.put("headers", httpHeaders);
        return attributes;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 计算请求时长
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("白名单={}", whitelistPaths);
        log.info("请求参数={}", getRequesttMap(exchange));

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();

        if (isWhitelist(path)) {
            try {
                /// header增加token
                ServerHttpRequest newRequest = exchange.getRequest().mutate().headers(headers -> {
                    headers.add("x-fetch-gateway-token", "ABC123");
                }).build();
                return chain.filter(exchange.mutate().request(newRequest).build()).then(Mono.fromRunnable(() -> {
                    stopWatch.stop();
                    log.info("请求={}m耗时={}", exchange.getRequest().getURI().getRawPath(), stopWatch.getTotalTimeSeconds());
                }));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token验证失败");
            }
        }

        String token = request.getHeaders().getFirst("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            // 抛出异常，由全局处理器捕获
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "登录失效");
        }

        try {
            validateHToken(token);
            /// header增加token
            ServerHttpRequest newRequest = exchange.getRequest().mutate().headers(headers -> {
                headers.add("x-fetch-gateway-token", "ABC123");
            }).build();
            return chain.filter(exchange.mutate().request(newRequest).build()).then(Mono.fromRunnable(() -> {
                stopWatch.stop();
                log.info("请求={}m耗时={}", exchange.getRequest().getURI().getRawPath(), stopWatch.getTotalTimeSeconds());
            }));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token验证失败");
        }
    }


    // 判断是否为白名单路径
    private boolean isWhitelist(String path) {
        if (CollectionUtils.isEmpty(whitelistPaths)) {
            return false;
        }
        return whitelistPaths.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    // 自行验证token
    private void validateHToken(String token) throws ResponseStatusException {

    }
}
