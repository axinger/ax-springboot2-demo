package com.ax.demo.controller;

import com.ax.hello.service.HelloService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName CustomizeStartController.java
 * @Description TODO
 * @createTime 2022年02月12日 18:15:00
 */
@Api(tags = {"springboot自定义start"})
@RestController
public class CustomizeStartController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/axStart")
    public Object hello() {
        final String sayHello = helloService.sayHello("jim");
        System.out.println("sayHello = " + sayHello);
        Map map = new HashMap();
        map.put("hello", sayHello);
        return map;
    }
}
