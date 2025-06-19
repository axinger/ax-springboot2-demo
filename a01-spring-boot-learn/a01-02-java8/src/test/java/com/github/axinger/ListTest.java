package com.github.axinger;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class ListTest {

    @Test
    public void test1() {

        /*
        增加：
        add(E e)：在链表后添加一个元素；
        addFirst(E e)：在链表头部插入一个元素；
        addLast(E e)：在链表尾部添加一个元素；


        删除：
        remove() ：移除链表中第一个元素;
        remove(index i)：移除指定元素；
        pop()：删除第一个元素
        poll()：返回第一个元素并移除

        查：
        get(int index)：按照下标获取元素；
        peek()：获取第一个元素，但是不移除；
        peekFirst()：获取第一个元素，但是不移除；
        peekLast()：获取最后一个元素，但是不移除；
        pollFirst()：查询并删除头；
        pollLast()：删除尾；
        poll()：查询并移除第一个元素
        */

        /*
        增加：
        add(E e)：在链表后添加一个元素；
        addFirst(E e)：在链表头部插入一个元素；
        addLast(E e)：在链表尾部添加一个元素；
         */
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);  //末尾添加   1
        list.addLast(2);  //末尾添加  1，2
        list.addFirst(3);  //头部添加   3，1，2
        System.out.println(list.toString());   //3，1，2
        System.out.println("------------------------------------------------------------------------------------------------------------------");


        /*
        删除：
        remove() ：移除链表中第一个元素;
        remove(index i)：移除指定元素；
        pop()：删除第一个元素
        poll()：返回第一个元素并移除
         */
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(i + 1);
        }
        System.out.println("当前元素有: " + list2.toString());//1,2,3,4,5,6,7,8,9,10

        list2.remove();   //删除第一个元素
        System.out.println("删除第一个元素后，链表为：" + list2.toString());   //2，3,4,5,6,7,8,9,10

        list2.remove(2);   //删除索引为2的元素
        System.out.println("删除索引为" + 2 + "的元素后，链表为：" + list2.toString());  //2，3,5,6,7,8,9,10

        list2.pop();
        System.out.println("删除第一个元素后，链表为：" + list2.toString());   //3,5,6,7,8,9,10

        System.out.println("删除第一个元素后，链表为：" + list2.toString());   //5,6,7,8,9,10
        System.out.println("删除元素为" + list2.poll());
        System.out.println("------------------------------------------------------------------------------------------------------------------");


        /*
        查：
        get(int index)：按照下标获取元素；
        peek()：获取第一个元素，但是不移除；
        peekFirst()：获取第一个元素，但是不移除；
        peekLast()：获取最后一个元素，但是不移除；
        pollFirst()：查询并删除头；
        pollLast()：删除尾；
        poll()：查询并移除第一个元素
         */
        LinkedList<Integer> list3 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list3.add(i + 1);
        }
        System.out.println("当前元素有: " + list3.toString());//1,2,3,4,5,6,7,8,9,10
        System.out.println("获取下标为3的元素:" + list3.get(3));  //4

        System.out.println("使用peek()获取第一个元素，但不删除,获取的元素为:" + list3.peek());  //1
        System.out.println("使用peek()后链表为：" + list3.toString());  //1,2,3,4,5,6,7,8,9,10

        System.out.println("使用peekFirst()获取第一个元素:" + list3.peekFirst());   //1
        System.out.println("使用peekLast()获取最后一个元素" + list3.peekLast());   //10
        System.out.println("使用peekFirst()和peekLast后链表为:" + list3.toString());   //1,2,3,4,5,6,7,8,9,10

        System.out.println("使用poll()查看并删除第一个元素，查看元素为" + list3.poll());    //1
        System.out.println("使用pollLast()查看并删除最后一个元素，查看元素为" + list3.pollLast());   //10
        System.out.println("使用poll()和pollLast()后链表为:" + list3.toString());    //2,3,4,5,6,7,8,9

    }
}
