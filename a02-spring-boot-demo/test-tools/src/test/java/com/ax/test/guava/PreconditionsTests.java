package com.ax.test.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName PreconditionsTests.java
 * @description TODO
 * @createTime 2022年05月02日 22:46:00
 */

public class PreconditionsTests {


    @Test
    void test1(){

       List list = new ArrayList();
        list.add("a");

//        System.out.println("checkElementIndex = " + Preconditions.checkElementIndex(1, list.size(), "越界"));
//
//        System.out.println("list = " + list);

        System.out.println("MoreObjects.firstNonNull(1,2) = " + MoreObjects.firstNonNull(1,null));


//        List<String> list = Arrays.asList(new String[10]);
//        list.set(1,"aa");
//        list.add("d");
//        System.out.println(list);

    }
}
