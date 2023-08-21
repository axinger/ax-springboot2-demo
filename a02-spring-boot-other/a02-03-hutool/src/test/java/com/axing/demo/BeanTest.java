package com.axing.demo;

import cn.hutool.core.bean.DynaBean;
import org.junit.jupiter.api.Test;

public class BeanTest {

    @Test
    void test1() {
        Person person = Person.builder()
                .build();
        DynaBean dynaBean = DynaBean.create(person);
        dynaBean.set("name", "jim");
        System.out.println("person = " + person);
        Object name = dynaBean.get("name");
        System.out.println("name = " + name);
    }
}
