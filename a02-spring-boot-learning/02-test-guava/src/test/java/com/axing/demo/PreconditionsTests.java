package com.axing.demo;

import com.google.common.base.MoreObjects;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void test1() {

        List list = new ArrayList();
        list.add("a");

//        System.out.println("checkElementIndex = " + Preconditions.checkElementIndex(1, list.size(), "越界"));

        System.out.println("list = " + list);

        System.out.println("返回不为空的 = " + MoreObjects.firstNonNull(null, 1));
        System.out.println("MoreObjects.toStringHelper(UserInfo.class) = " + MoreObjects.toStringHelper(UserInfo.class));


        final MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(list);


//        List<String> list = Arrays.asList(new String[10]);
//        list.set(1,"aa");
//        list.add("d");
//        System.out.println(list);

    }
}
