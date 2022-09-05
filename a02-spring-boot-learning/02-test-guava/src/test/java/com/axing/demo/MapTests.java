package com.axing.demo;

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
}
