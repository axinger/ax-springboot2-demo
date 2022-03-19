package com.ax.test.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName ListTest.java
 * @description TODO
 * @createTime 2022年03月24日 14:04:00
 */

public class ListTest {

    @Test
    void test1(){
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
            if (integer ==2){
                break;
            }
        }
    }
}
