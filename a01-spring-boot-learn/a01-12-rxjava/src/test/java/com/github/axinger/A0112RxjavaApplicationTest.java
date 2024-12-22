package com.github.axinger;


import io.reactivex.Observable;
import org.junit.jupiter.api.Test;



public class A0112RxjavaApplicationTest {

    @Test
    void test1() {

        Observable<String> observable = Observable.create(observer -> {
            observer.onNext("Hello, world!");
            observer.onComplete();
        });
//
//        observable.subscribe(System.out::println);

        Observable.just("Hello Tester1")
                .doOnNext(s -> System.out.println("doOnNext：" + s))
                .doFinally(() -> System.out.println("doFinally1"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate1"))
                .doFinally(() -> System.out.println("doFinally2"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate2"))
                .subscribe(
                        s -> System.out.println("onNext：" + s),
                        throwable -> System.out.println("onError" + throwable),
                        () -> System.out.println("onComplete"));

    }

}
