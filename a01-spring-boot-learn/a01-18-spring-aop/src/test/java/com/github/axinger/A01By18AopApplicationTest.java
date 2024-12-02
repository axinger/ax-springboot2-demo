package com.github.axinger;

import com.github.axinger.controller.TestController;
import com.github.axinger.service.LogService1;
import com.github.axinger.service.UserService;
import com.github.axinger.service2.LogService2;
import com.github.axinger.service2.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class A01By18AopApplicationTest {

    @Autowired
    UserService service;
    @Autowired
    PersonService personService;
    @Autowired
    TestController testController;
    @Autowired
    private LogService1 logService1;
    @Autowired
    private LogService2 logService2;

    @Test
    void test1() {
        String str = service.add("1", "2");
        System.out.println("str = " + str);
    }

    // PersonServiceImpl personService;
    @Test
    void test2() {
        String str = personService.add("1", "2");
        System.out.println("str = " + str);

        // String st2 = personService.add2("1", "2");
        // System.out.println("st2 = " + st2);

        // String add3 = personService.add3("1", "2");
        // System.out.println("add3 = " + add3);
    }

    @Test
    void test_TestController() {
        testController.index();
    }

    @Test
    void test_TestController1() {

        logService1.log("tom");
    }

    @Test
    void test3() {
        logService2.log("jim");
    }
}
