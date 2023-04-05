package com.axing.demo;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTest {
    @Test
    public void test1() {

        /***
         * Collection 子接口 List
         *
         * 1.ArrayList 主要实现类
         * 2.LinkedList
         * 3.Vector 古老实现类
         */
        List arrayList = new ArrayList();
        LinkedList<Object> linkedList = new LinkedList<>();
        Vector<Object> vector = new Vector<>();
        arrayList.add(1);
        arrayList.add(2);
//        arrayList.remove(2);//remove 是index,
//        arrayList.remove(new Integer(2));

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }

    }

    @Test
    public void test2() {
        HashSet<Object> hashSet = new HashSet<>();
        hashSet.add(123);
        hashSet.add("a");
        hashSet.add(Person.builder()
                .id(1)
                .build());
        hashSet.add(Person.builder()
                .id(2)
                .build());

        hashSet.forEach(e -> {
            System.out.println("e = " + e);
        });
//        hashSet.stream().forEach(e->{
//            System.out.println("e = " + e);
//        });
        new LinkedHashSet<>();


        /// 相同对象, 判断重复,根据 compareTo 进行判断
        TreeSet<Object> treeSet = new TreeSet<>();
//        treeSet.add(new Integer(1));
        treeSet.add(Person.builder()
                .id(1)
                .build());
        treeSet.add(Person.builder()
                .id(1)
                .build());
        treeSet.add(Person.builder()
                .id(1)
                .build());
        System.out.println("treeSet = " + treeSet);
        treeSet.forEach(e -> {
            System.out.println("e = " + e);
        });

    }

}
