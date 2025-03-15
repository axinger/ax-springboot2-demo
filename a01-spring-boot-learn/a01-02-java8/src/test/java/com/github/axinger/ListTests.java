package com.github.axinger;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListTests {


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
        List<Integer> list = List.of(1, 3, 2, 4);

        System.out.println("list.stream().unordered() = " + list.stream().unordered().toList());
        ;
        System.out.println("list.stream().sorted() = " + list.stream().sorted().toList());
    }

    @Test
    void test4() {
        List<Integer> list = List.of(1, 3, 2, 4);
        System.out.println("list.indexOf(5) = " + list.indexOf(5));
    }

    @Test
    void test5() {

        User user1 = new User();
        user1.setId(1);
        user1.setName("jim");

        User user2 = new User();
        user2.setId(1);
        user2.setName("tom");
        List<User> list = List.of(user1, user2);

        // Map<Integer, User> maps2 = list.stream().collect(Collectors.toMap(User::getId,Function.identity()));

        // 另外，转换成map的时候，可能出现key一样的情况，如果不指定一个覆盖规则，上面的代码是会报错的。转成map的时候，最好使用下面的方式：
        Map<Integer, User> map = list.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key1));
        System.out.println("map = " + map);

        Map<Integer, String> collect = list.stream().collect(Collectors.toMap(User::getId, User::getName, (key1, key2) -> key2));

        System.out.println("collect = " + collect);
    }

    @Test
    @SuppressWarnings("all")
    void test6() {
        /// 可以
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer i : list) {
            if (i == 2) {
                list.remove(i);
            }
        }
        System.out.println("list = " + list);
    }

    @Test
    void test7() {
        /// 可以
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeIf(i -> i == 2);
        System.out.println("list = " + list);
    }

    @Test
    @SuppressWarnings("all")
    void test8() {
        /// 可以, 建议使用removeIf
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 2) {
                iterator.remove();
            }
        }
        System.out.println("list = " + list);
    }

    @Test
    void test9() {
        /// 可以
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for (int i = 0; i < list.size(); i++) {
            Integer obj = list.get(i);
            if (obj == 2) {
                list.remove(obj);
            }
        }
        System.out.println("list = " + list);
    }
}
