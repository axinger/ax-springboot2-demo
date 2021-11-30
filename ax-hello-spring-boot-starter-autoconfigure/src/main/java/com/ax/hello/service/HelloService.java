package com.ax.hello.service;

import com.ax.hello.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认不要放在容器中
 */
public class HelloService {

    @Autowired
    private HelloProperties helloProperties;

    public String sayHello(String name) {

        return helloProperties.getPrefix() + " : " + name + " : " + helloProperties.getSuffix();


    }


}
