package com.ax.aop;

import com.ax.aop.config.AopConfig;
import com.ax.aop.model.AopUser;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test2 {

    @Test
    public void test1() {


        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        AopUser user = context.getBean("aopUser", AopUser.class);
        System.out.println("user = " + user);
        user.add();
    }

}
