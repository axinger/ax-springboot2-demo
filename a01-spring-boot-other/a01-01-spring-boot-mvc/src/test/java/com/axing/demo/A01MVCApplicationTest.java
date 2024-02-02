package com.axing.demo;

import com.axing.demo.bean.HumitureRuleProperties;
import com.axing.demo.event.MyCustomEvent;
import com.axing.demo.event.MyCustomEvent2;
import com.axing.demo.model.Person;
import com.axing.demo.statemachine.Events;
import com.axing.demo.statemachine.States;
import com.axing.same.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest
class A01MVCApplicationTest {

    @Autowired
    UserService userService;
    @Autowired
    StateMachine<States, Events> stateMachine;
    // @Autowired
    // private UserService userService;
    //
    // @Test
    // void test_UserService(){
    //     System.out.println("userService = " + userService);
    // }
    @Autowired
    private HumitureRuleProperties humitureRuleProperties;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private WebClient webClient;

    @Test
    void test2() {
        HumitureRuleProperties.HumidityDto humidity = humitureRuleProperties.humidity();
        System.out.println("humidity = " + humidity);
        System.out.println("humitureRuleProperties.humidity().max() = " + humitureRuleProperties.humidity().max());
        System.out.println("humitureRuleProperties = " + humitureRuleProperties);
    }

    @Test
    void test_UserService2() {
        System.out.println("userService2 = " + userService);
    }

    @Test
    void test_event() {
        applicationEventPublisher.publishEvent(new MyCustomEvent("123"));
        applicationEventPublisher.publishEvent(new MyCustomEvent2("abc"));
    }

    @Test
    void test_event2() {
        applicationEventPublisher.publishEvent(new MyCustomEvent2("abc"));
    }

    @Test
    void test_10() {


        Mono<ResponseEntity<String>> lengleng = webClient.get()
                .uri("?name={name}&type={type}", "lengleng", "1")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);


        ResponseEntity<Void> response = webClient.post()
                .uri("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new Person())
                .retrieve()
                .toBodilessEntity()
                .block();

        if (response.getStatusCode().is2xxSuccessful()) {
//            logger.info("Created " + response.getStatusCode());
//            logger.info("New URL " + response.getHeaders().getLocation());
        }


    }

    @Test
    void test_statemachine() {
//        stateMachine.start();
//        stateMachine.sendEvent(Events.ONLINE);
//        stateMachine.sendEvent(Events.PUBLISH);
//        stateMachine.sendEvent(Events.ROLLBACK);


        Message<Events> event = MessageBuilder.withPayload(Events.ONLINE).build();

        stateMachine.sendEvent(Mono.just(event))
                .doOnComplete(() -> {
                    System.out.println("Event handling complete");
                })
                .subscribe();

    }

}
