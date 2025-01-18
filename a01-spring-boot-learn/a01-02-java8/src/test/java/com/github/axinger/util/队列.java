package com.github.axinger.util;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class 队列 {

    @Test
    public void test() {

        //移除并返回队头元素
//        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        int remove = queue.remove();
//        输出：1
        System.out.println("remove = " + remove);

    }

    @Test
    public void test2() {
        //获取队头元素
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        int peek = queue.peek();
//        输出：1
        System.out.println("peek = " + peek);
    }

    @Test
    public void test3() {
        //队列判空
        Queue<Integer> queue = new LinkedList<>();
        boolean isEmpty = queue.isEmpty();
        System.out.println("isEmpty = " + isEmpty);
//        输出：true
    }
}
