package com.axing.demo;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName Instance.java
 * @description TODO
 * @createTime 2022年07月08日 16:01:00
 */

public class Instance {
    @Test
    void test2() {
        List<Object> list = new ArrayList();
        ;
        list.add("1");
        list.add(2);

        final List<HashMap<Object, Object>> collect = list.parallelStream()
                .map(e -> {
                    return new HashMap<>();
                }).collect(Collectors.toList());

    }

    @Test
    void test1() {

        Map<String, Object> map = new HashMap<>();
        map.put("list", Arrays.asList(1, 2));

        System.out.println("map = " + map);


        final Object list = map.get("list1");

        System.out.println("(list instanceof List) = " + (list instanceof List));


    }
}
