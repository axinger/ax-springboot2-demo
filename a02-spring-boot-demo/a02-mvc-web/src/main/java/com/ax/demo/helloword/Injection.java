package com.ax.demo.helloword;

import org.springframework.stereotype.Service;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName DDModel.java
 * @Description TODO
 * @createTime 2022年02月09日 14:36:00
 */

@Service
public class Injection {



    private InjectionService injectionService;

//    private HelloService helloService;
//
//    // setter方式注入Bean
    public void setInjectionService(InjectionService injectionService) {
        System.out.println("注入 = setter");
        this.injectionService = injectionService;
    }
//
//    // 构造方法注入
//    public Injection (InjectionService injectionService) {
//        System.out.println("注入 = 构造器注入");
//        this.injectionService = injectionService;
//    }

    public void doSome() {
        System.out.println("doSome.......");
        // 向大家打招呼
        injectionService.sayHello("大家好!");
    }

}

