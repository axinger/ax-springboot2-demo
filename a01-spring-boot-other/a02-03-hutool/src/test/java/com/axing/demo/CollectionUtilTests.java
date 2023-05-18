package com.axing.demo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName CollectionUtilTests.java
 * @description TODO
 * @createTime 2022年06月13日 09:17:00
 */

public class CollectionUtilTests {

    @Test
    public void test_0() {
//        new ArrayList<>(Collections.singleton("1"));
//
//        Arrays.asList("1").remove(1);

        List<String> listA = ListUtil.of("1", "2", "3", "4", "5");
        List<String> listB = ListUtil.of("1", "2", "3", "7");


        Collection<String> result = ObjectUtil.clone(listA);

        System.out.println("listA = " + System.identityHashCode(listA));
        System.out.println("result = " + System.identityHashCode(listA));


//        result.removeAll(listB);
    }

    @Test
    public void test1() {


//        List<String> listA = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
//
//        List<String> listB = new ArrayList<>(Arrays.asList("1", "2", "3", "7"));

        List<String> listA = ListUtil.toList("1", "2", "3", "4", "5");
        List<String> listB = ListUtil.of("1", "2", "3", "7");


        System.out.println("集合A:" + listA);
        System.out.println("集合B:" + listB);
        System.out.println("------------------------");

        // 并集
        Collection<String> union = CollectionUtil.union(listA, listB);
        System.out.println("并集 union ：" + union);


        // 交集
        Collection<String> intersection = CollectionUtil.intersection(listA, listB);
        System.out.println("交集 intersection :" + intersection);
        // 交集的补集
        Collection<String> disjunction = CollectionUtil.disjunction(listA, listB);
        System.out.println("交集的补集  disjunction ：" + disjunction);
        // 差集(集合相减)
        Collection<String> subtract = CollectionUtil.subtract(listA, listB);
        System.out.println("差集(集合相减) subtract  ：" + subtract);

    }

    @Test
    public void test1_2() {

        List<String> listA = Stream.of("甲","乙","丙").toList();
        List<String> listB = Stream.of("丙").toList();

        Collection<String> subtract = CollectionUtil.subtract(listA, listB);
        Collection<String> subtract2 = CollectionUtil.subtractToList(listA, listB);
        System.out.println("差集(集合相减) subtract  ：" + subtract);
        System.out.println("subtract2 = " + subtract2);
    }

    @Test
    public void test2() {


        List<String> listA = ListUtil.toList("1", "2", "3", "4", "5");
        List<String> listB = ListUtil.of("1", "2");

        // 交集的补集
        Collection<String> disjunction = CollectionUtil.disjunction(listA, listB);
        System.out.println("交集的补集  disjunction ：" + disjunction);


        Map map = new HashMap<>() {{
            put("1", "1");
            put("3", "3");
        }};


        Collection<String> disjunction2 = CollectionUtil.disjunction(listA, map.keySet());
        System.out.println("disjunction2 = " + disjunction2);
    }
}
