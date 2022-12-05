package com.axing.demo;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTests {

    @Test
    void test() {

        HashMap<Object, Object> map = Maps.newHashMap(new HashMap<>() {{
            put("a", 1);
        }});
        System.out.println("map = " + map);

        Map<Object, Object> map1 = new HashMap<>() {{
            put("a", 1);
        }};
        System.out.println(map1);
    }


    @Test
    void map_filterKeys() {
        Map<Object, Object> map1 = Maps.newHashMap();
        map1.put("name", "jim");
        map1.put("age", 20);
        // map过滤
        final Map<Object, Object> filterKeys = Maps.filterKeys(map1, key -> key.equals("name"));
        System.out.println("filterKeys = " + filterKeys);
    }

    @Test
    void map() {
        Map<Object, Object> map1 = Maps.newHashMap();
        map1.put("name", "jim");
        map1.put("age", 20);

        Map<Object, Object> map2 = Maps.newHashMap();
        map2.put("name", "tom");
        map2.put("high", 20);
        // 交集
        MapDifference<Object, Object> difference = Maps.difference(map1, map2);

        System.out.println("求两个map集合的差集,key相同,value不同 = " + difference);

        // 左边差集
        final Map<Object, Object> onlyOnLeft = difference.entriesOnlyOnLeft();
        System.out.println("map1 独有 = " + onlyOnLeft);
        // 右边差集
        final Map<Object, Object> onlyOnRight = difference.entriesOnlyOnRight();
        System.out.println("map2 独有 = " + onlyOnRight);
        System.out.println("difference.entriesDiffering() = " + difference.entriesDiffering());
        System.out.println("difference.entriesInCommon() = " + difference.entriesInCommon());


    }
}
