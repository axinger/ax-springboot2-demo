package com.axing.demo;

import com.axing.demo.bean.HumitureRuleProperties;
import com.axing.demo.bean.MyProperties;
import com.axing.same.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvcApplicationTest {

    @Autowired
    UserService userService;
    @Autowired
    private MyProperties myProperties;

    // @Autowired
    // private UserService userService;
    //
    // @Test
    // void test_UserService(){
    //     System.out.println("userService = " + userService);
    // }
    @Autowired
    private HumitureRuleProperties humitureRuleProperties;

    @Test
    void test1() {
        System.out.println("userConfig.getList() = " + myProperties);
        String username = myProperties.user().username();
        System.out.println("username = " + username);
    }

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


}
