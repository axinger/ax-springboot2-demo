package com.ax.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName SplitterTest.java
 * @description TODO
 * @createTime 2022年03月22日 17:22:00
 */

public class SplitterTest {

    @Test
    void test1() {
        String str = "1,2,3,4,5";
        List<String> list = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);


        Map<String, Object> valMap = new HashMap(1);
        valMap.put("na", 1);
        if (valMap.size() < 1) {
            System.out.println("长度不够");
        }

        final String content = JSON.toJSONString(valMap);
        System.out.println("content = " + content);
    }
}
