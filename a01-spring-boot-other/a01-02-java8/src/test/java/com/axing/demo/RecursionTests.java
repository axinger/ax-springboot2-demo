package com.axing.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 递归
 */
public class RecursionTests {


    @Test
    void test() {

        Map<String, Object> map = new HashMap<>();
        map.put("1", "1");

        List<Map<String, Object>> list1 = new ArrayList<>();
        list1.add(getMap("list1-1", "aa"));
        list1.add(getMap("list1-2", "bb"));
        map.put("list1", list1);
        System.out.println("map = " + map);


        Map<String, Object> map2 = new HashMap<>();

        map.forEach((k, v) -> {
            map2.put(k, getObject(v));
        });

        System.out.println("map2 = " + map2);

    }

    Map getMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        map.put(key + "a", value);
        return map;
    }


    Object getObject(Object obj) {
        if (obj instanceof String) {
            return obj + "c";
        }

        if (obj instanceof List list) {
            List list2 = new ArrayList<>();
            list.forEach((k) -> {
                list2.add(getObject(k));
            });
            return list2;
        }


        if (obj instanceof Map map) {

            Map map1 = new HashMap<>();

            map.forEach((k, v) -> {
                map1.put(k, getObject(v));
            });
            return map1;
        }

        return obj;
    }


}
