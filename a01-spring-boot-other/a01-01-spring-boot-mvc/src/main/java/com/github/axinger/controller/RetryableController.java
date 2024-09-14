package com.github.axinger.controller;

import com.axing.common.response.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@Tag(name = "重试功能", description = "RetryableController")
@Slf4j
public class RetryableController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/testRetryable")
    public Result<?> test() {

        //走异步，记录
        CompletableFuture.runAsync(() -> {
            Map<String, Object> map = exampleService.test1();
            System.out.println("map = " + map);
        });
        return Result.ok();
//        return null;
    }


    @Service
    static public class ExampleService {

        /**
         * interceptor：可以通过该参数，指定方法拦截器的bean名称
         * value：抛出指定异常才会重试
         * include：和value一样，默认为空，当exclude也为空时，默认所以异常
         * exclude：指定不处理的异常
         * maxAttempts：最大重试次数，默认3次
         * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；
         * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
         *
         * @return
         */
        @Retryable(value = {Exception.class}, maxAttempts = 4, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
        public Map<String, Object> test1() {
            log.info("test1===========");
            int i = 1 / 0;
            return Map.of("data", "不应该返回");
        }

        @Recover
        public Map<String, Object> doRecover(Exception e) throws ArithmeticException {
            log.info("全部重试失败，执行doRecover");
            return Map.of("data", "保底返回值");
        }
    }


}
