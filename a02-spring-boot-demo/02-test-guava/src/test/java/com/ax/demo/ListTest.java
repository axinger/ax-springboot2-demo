package com.ax.demo;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName ListTest.java
 * @description TODO
 * @createTime 2022年03月22日 17:08:00
 */

public class ListTest {

    @Test
    void test1() {

        List list = Lists.newArrayList(1, 2, 3, 4);

        final List list1 = Lists.partition(list, list.size() - 1);
        System.out.println("Lists.partition(list, 3) = " +list1);
        if (list1.size()>1){
            System.out.println("list.get(1) = " + list1.get(1));

        }


    }
}
