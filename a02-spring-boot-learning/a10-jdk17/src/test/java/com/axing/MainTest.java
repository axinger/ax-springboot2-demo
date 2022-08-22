package com.axing;

import com.axing.model.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


class MainTest {

    @Test
    void test() {
        Person person = new Person();
        person.setName("jim");
        System.out.println("person = " + person);

        List<Integer> integers = List.of(1, 2, 3);
        System.out.println("integers = " + integers);

        Map<String, Integer> map = Map.of("a", 1, "b", 2, "c", 3, "d", 4);
        System.out.println("map = " + map);

        List<String> list = Arrays.asList("1");
//        list.add("3");
        System.out.println("list = " + list);

    }
}