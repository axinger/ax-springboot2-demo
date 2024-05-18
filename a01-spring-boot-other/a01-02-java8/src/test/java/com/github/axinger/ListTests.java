package com.github.axinger;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListTests {

    // @Test
    // void test1(){
    //     List<Integer> list = List.of(1, 2, 3, 4);
    //
    //
    //     Map<Long, String> maps = list.stream().collect(Collectors.toMap(User::getId, User::getName, (key1, key2) -> key2));
    //
    // }


    @Test
    void test1() {

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
}
