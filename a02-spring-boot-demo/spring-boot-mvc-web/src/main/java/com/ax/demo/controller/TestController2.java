package com.ax.demo.controller;

import com.ax.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author xing
 */
@Controller
public class TestController2 {


//    @Autowired
//    private HelloService helloService;

    /**
     * 内容协商
     * 1. 浏览器请求 返回html [application/xml] xmlConverter
     * 2. ajax请求,返回json [application/json] jsonConverter
     * 3. app客户端, 返回自定义协议数据 [application/x-自定义的] xxxConverter
     * <p>
     * 步骤
     * 1,添加messageConverter系统底层
     * <p>
     * http://localhost:8080/test41?format=xml json gg
     */
    @GetMapping(value = "/test41")
    @ResponseBody
    public Person test41() {

        System.out.println("test41............");
        Person person = new Person();
        person.setName("jim");
        person.setAge(12);
        return person;
    }

//    @RequestMapping(value = "/test51")
//    @ResponseBody
//    public Object hello() {
//        final String sayHello = helloService.sayHello("jim");
//        System.out.println("sayHello = " + sayHello);
//        Map map = new HashMap();
//        map.put("hello", sayHello);
//        return map;
//    }


}
