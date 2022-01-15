package com.ax.demo_java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Arrays 数组工具, String[] 这样的才是数组 list是集合
 *
 */
public class ArraysTest {

    /// 数组转字符串
    @Test
    public void test1() {

        int[] array = new int[]{1, 2, 3};
        System.out.println("array = " + array);
        System.out.println("Arrays.toString(array) = " + Arrays.toString(array));
    }

    /// 但遇到多维数组时，
    @Test
    public void test2() {
        int[][] deepArray = new int[][]{{1, 3}, {2, 4}};
        System.out.println(Arrays.toString(deepArray)); //[[I@1540e19d, [I@677327b6]
        System.out.println(Arrays.deepToString(deepArray)); //[[1, 3], [2, 4]]
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


    }


    @Test
    public void test4() {
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;


        List<Student> list3 = null;
        List<Person> list4 = null;
        List<Object> list5 = null;
        list1 = list3;
        list1 = list4;
//        list1 = list5; // 这个不行

//        list2 = list3; // 这个不行
        list2 = list4;
        list2 = list5;


    }
}
