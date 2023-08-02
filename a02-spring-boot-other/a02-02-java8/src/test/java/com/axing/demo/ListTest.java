package com.axing.demo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName ListTest.java
 * @description TODO
 * @createTime 2022年03月24日 14:04:00
 */

public class ListTest {

    @Test
    void test1() {
//
//        Arrays.asList(1,2,3,4).stream().forEach(val->{
//
//            if (val ==2){
//                return;
//            }
//            System.out.println("val = " + val);
//        });

        for (Integer integer : Arrays.asList(1, 2, 3, 4)) {

            System.out.println("val = " + integer);
            if (integer == 2) {
                break;
            }
        }
    }

    @Test
    void test2() {
        List<String> list = new ArrayList<>();
        System.out.println("list.get(0) = " + list.get(0));
    }


    @Test
    void test3() {
        List<Integer> list = List.of(1,3,2,4);

        System.out.println("list.stream().unordered() = " + list.stream().unordered().toList());;
        System.out.println("list.stream().sorted() = " + list.stream().sorted().toList());
    }

    @Test
    void test4() {
        List<Integer> list = List.of(1,3,2,4);
        System.out.println("list.indexOf(5) = " + list.indexOf(5));
    }
}
