package com.github.axinger;

import com.github.axinger.model.bean.PersonProperties;
import com.github.axinger.model.bean.UserProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class A01MVCApplicationTest {


    @Autowired
    private PersonProperties personProperties;

    @Test
    public void test() {
        System.out.println("personProperties = " + personProperties);
    }

    @Autowired
    private UserProperties userProperties;

    @Test
    public void test2() {
        System.out.println("userProperties = " + userProperties);

        String all = userProperties.all();
        System.out.println("all = " + all);
//        UserProperties.Dog dog = new UserProperties.Dog(1,2);
//        int min = dog.min();
//        System.out.println("min = " + min);
        int min = userProperties.dog().min();
        System.out.println("min = " + min);
    }
}
