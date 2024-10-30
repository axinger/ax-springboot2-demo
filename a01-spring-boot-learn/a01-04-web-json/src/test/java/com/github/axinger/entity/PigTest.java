package com.github.axinger.entity;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class PigTest {

    @Test
    void test() {

        Map map = new HashMap();
        map.put("aName", "jim");
        map.put("aAge", 10);
        Pig pig = JSON.parseObject(JSON.toJSONString(map), Pig.class);
        System.out.println("pig = " + pig);

        System.out.println("pig = " + JSON.toJSONString(pig));

    }
}
