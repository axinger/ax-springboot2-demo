package com.ax.demo;

import cn.hutool.core.collection.CollectionUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName CollectionUtilTests.java
 * @description TODO
 * @createTime 2022年06月13日 09:17:00
 */

public class CollectionUtilTests {

    @Test
    public void test1() {

        List<String> listA = new ArrayList<String>();
        listA.add("1");
        listA.add("2");
        listA.add("2");
        listA.add("3");
        List<String> listB = new ArrayList<String>();
        listB.add("1");
        listB.add("4");
        listB.add("5");
        System.out.println("集合A:" + listA);
        System.out.println("集合B:" + listB);
        System.out.println("------------------------");

        //并集
        Collection<String> union = CollectionUtil.union(listA, listB);
        System.out.println("并集：" + union);


        //交集
        Collection<String> intersection = CollectionUtil.intersection(listA, listB);
        System.out.println("交集：" + intersection);
        //交集的补集
        Collection<String> disjunction = CollectionUtil.disjunction(listA, listB);
        System.out.println("交集的补集   ：" + disjunction);
        //差集(集合相减)
        Collection<String> subtract = CollectionUtil.subtract(listA, listB);
        System.out.println("差集(集合相减)   ：" + subtract);
    }
}
