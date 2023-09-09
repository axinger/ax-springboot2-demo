package com.github.axinger;


import com.alibaba.fastjson.JSONPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    @Test
    void test53() {
        List<Person> list = new ArrayList<>();

        Person person = new Person();
        person.setId("1");
        person.setAge(10);

        list.add(person);


        Object eval = JSONPath.eval(person, "[age = 10]");
        System.out.println("eval = " + eval);

        Object eval2 = JSONPath.eval(person, "[id = '1']");
        System.out.println("eval = " + eval2);
    }
}
