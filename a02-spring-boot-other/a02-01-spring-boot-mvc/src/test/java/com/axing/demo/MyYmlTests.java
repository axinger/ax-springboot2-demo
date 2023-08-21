package com.axing.demo;

import com.axing.demo.bean.MyYmlBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyYmlTests {

    @Autowired
    private MyYmlBean myYmlBean;

    @Test
    void test1() {
        System.out.println("userConfig.getList() = " + myYmlBean);
        String username = myYmlBean.user().getUsername();
        System.out.println("username = " + username);

    }
}
