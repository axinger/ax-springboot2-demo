package com.github.axinger;


import com.alibaba.fastjson.JSONPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    @Test
    void test53() {


        Person person = new Person();
        person.setId("1");
        person.setAge(10);
        Object eval = JSONPath.eval(person, "[age = 10]");
        System.out.println("eval = " + eval);


        Object eval2 = JSONPath.eval(person, "[id = '1']");
        System.out.println("eval = " + eval2);

        List<Person> list = new ArrayList<>();
        list.add(person);

        {
            Person person2 = new Person();
            person2.setId("2");
            person2.setAge(10);
            list.add(person2);
        }
        Object eval3 = JSONPath.eval(list, "[id in ('1')]");
        System.out.println("eval3 = " + eval3);

        Object eval4 = JSONPath.eval(list, "[id in ('1','2')]");
        System.out.println("eval4 = " + eval4);
    }
}
