package com.ax.annotations;

import com.ax.annotations.config.SpringConfig;
import com.ax.annotations.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tests {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beanTwo1.xml");
        PersonService personService = context.getBean("personService", PersonService.class);;
        System.out.println("personService = " + personService);
        personService.add();

    }

    /**
     * 加载注解
     * */
    @Test
    public void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PersonService personService = context.getBean("personService", PersonService.class);;
        System.out.println("配置类方式 personService = " + personService);
        personService.add();

    }
}
