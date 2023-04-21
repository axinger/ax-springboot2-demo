package com.axing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/f")
@Slf4j
public class FluxController {

    // @GetMapping(path = "/1", produces = MediaType.APPLICATION_STREAM_JSON_VALUE,
    //         consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
    // public Flux<User> streamNumbers2(@RequestBody Publisher<Integer> request)
    //         throws InterruptedException {
    //     return Flux.from(request).map(item -> {
    //         try {
    //             Thread.sleep(1000);
    //
    //             return User.builder()
    //                     .id(1)
    //                     .name("jim")
    //                     .build();
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //         return null;
    //     }).doOnNext(item -> log.info("Number: {}", item));
    // }


    @GetMapping(value = "/1", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() throws Exception {
        return Flux.interval(Duration.ofMillis(1000)).map(i -> i + "");
    }

    @GetMapping(value = "/2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux2() {
        return Flux.generate(val -> {
            val.next("jim");
            val.complete();
        });
    }

    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux3() {
        return Flux.generate(val -> {
            val.next("jim");
            val.complete();
        });
    }
}
