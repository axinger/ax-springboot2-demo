package com.ax.demo.helloword;

import org.springframework.stereotype.Service;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName HelloService.java
 * @Description TODO
 * @createTime 2022年02月09日 14:37:00
 */

@Service
public class InjectionService {

    public void sayHello(String name) {
        System.out.println("InjectionService = " + name);
    }

}
