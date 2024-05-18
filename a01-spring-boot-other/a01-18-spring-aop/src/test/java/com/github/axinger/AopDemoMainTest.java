package com.github.axinger;

import com.github.axinger.controller.TestController;
import com.github.axinger.service.UserService;
import com.github.axinger.service2.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AopDemoMainTest {

    @Autowired
    UserService service;
    @Autowired
    PersonService personService;
    @Autowired
    TestController testController;

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
}
