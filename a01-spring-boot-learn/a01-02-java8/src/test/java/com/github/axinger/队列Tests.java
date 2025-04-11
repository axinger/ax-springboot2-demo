package com.github.axinger;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class 队列Tests {

    @Test
    public void test() throws InterruptedException {

        //移除并返回队头元素
//        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean add = queue.add(1);
        queue.add(2);

//        add(E e): 将指定的元素插入到队列中。如果插入失败（例如队列已满），则抛出 IllegalStateException 异常。
//        offer(E e): 将指定的元素插入到队列中。如果插入失败，则返回 false，不会抛出异常。
        boolean offer = queue.offer(3);

        TimeUnit.SECONDS.sleep(1);

//        remove(): 移除并返回队列头部的元素。如果队列为空，则抛出 NoSuchElementException 异常。
//        poll(): 移除并返回队列头部的元素。如果队列为空，则返回 null，不会抛出异常。
//        输出：1
        System.out.println("remove1 = " + queue.remove());
        System.out.println("queue1 = " + queue);

        TimeUnit.SECONDS.sleep(1);
        System.out.println("remove2 = " + queue.remove());
        System.out.println("queue2 = " + queue);
    }

    @Test
    public void test2() {
        // 查看队列头部元素，但不移除
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        int peek = queue.peek();
//        输出：1
        System.out.println("peek = " + peek);
        System.out.println("queue = " + queue);
    }

    @Test
    public void test3() {
        //队列判空
        Queue<Integer> queue = new LinkedList<>();
        boolean isEmpty = queue.isEmpty();
        System.out.println("isEmpty = " + isEmpty);
//        输出：true
    }


    @Test
    public void test4() {
        /// 根据元素的自然顺序或指定的比较器顺序对元素进行排序。
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(3);
        pq.offer(1);
        pq.offer(2);
        System.out.println("pq = " + pq);

        System.out.println("优先级队列内容: " + pq);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    @Test
    public void test5() {
        /// 支持从两端操作的队列，例如 ArrayDeque。
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addLast(2);

        System.out.println("双端队列内容: " + deque);
    }

}
