package com.ax.test.java;

import com.ax.demo.Person;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionsTest {

    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 23);
    }

    @Test
    public void test3() {
        ArrayList list = new ArrayList<Person>() {
            {
                add(new Person("jim", 10));
            }
        };

        Person p2 = new Person("tom", 10);

        list.add(p2);


        System.out.println("list = " + list);


        /// 注意点
        ArrayList copyList = new ArrayList(Arrays.asList(new Object[list.size()]));
//        List copyList = Arrays.asList(new Object[list.size()]);
//        ArrayList copyList = new ArrayList<Person>();
        Collections.copy(copyList, list);
        System.out.println("copyList = " + copyList);

        /// 返回线程安全的集合
        List synchronizedList = Collections.synchronizedList(copyList);
    }


    @Test
    void test_map(){

        Map<String, Object> map = new HashMap<>();

        map.put("a",1);
        map.put("b",2);
        map.put("c",3);

        System.out.println("map.entrySet() = " + map.entrySet());

        map.entrySet().parallelStream().forEach(val->{

            System.out.println("val.getKey() = " + val.getKey());
            System.out.println("val.getValue() = " + val.getValue());
        });

    }

}
