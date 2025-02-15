package com.github.axinger.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionTests {


    @Test
    void test1() {
        Set<String> set1 = new HashSet<>(List.of("001", "002", "004"));

        Set<String> set2 = new HashSet<>(List.of("002", "004"));

        /// 交集
        set1.retainAll(set2);

        System.out.println("交集 = " + set1);

    }

    @Test
    void test2() {
        Set<String> set1 = new HashSet<>(List.of("001", "002", "004"));
        Set<String> set2 = new HashSet<>(List.of("002", "004", "005"));


        // 并集
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("并集 = " + union);

        // 交集
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("交集 = " + intersection);

        // 差集
        Set<String> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("差集 = " + difference);


        // 计算 set1 有但 set2 没有的元素
        Set<String> diff1 = new HashSet<>(set1);
        diff1.removeAll(set2); // 结果: ["001"]
        System.out.println("计算 set1 有但 set2 没有的元素 = " + diff1);

        Set<String> result = Stream.concat(set1.stream(), set2.stream())
                .filter(e -> !(set1.contains(e) && set2.contains(e)))
                .collect(Collectors.toSet());
        System.out.println("互相不存在" + result); // 输出: [001, 005]


        // 判断子集
        boolean isSubset = set1.containsAll(set2);
        System.out.println("set1 是否包含 set2 的所有元素 = " + isSubset);

    }
}
