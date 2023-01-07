package com.axing.demo;

import com.axing.demo.service.UserService;
import com.axing.demo.service2.PersonService;
import com.axing.demo.service2.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopDemoMainTest {

    @Autowired
    UserService service;

    @Test
    void test1() {
        String str = service.add("1", "2");
        System.out.println("str = " + str);
    }

    @Autowired
    PersonService personService;
    // PersonServiceImpl personService;
    @Test
    void test2() {
        String str = personService.add("1", "2");
        System.out.println("str = " + str);

        String st2 = personService.add2("1", "2");
        System.out.println("st2 = " + st2);

        // String add3 = personService.add3("1", "2");
        // System.out.println("add3 = " + add3);
    }
}
