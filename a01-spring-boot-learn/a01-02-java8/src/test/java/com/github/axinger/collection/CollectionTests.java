package com.github.axinger.collection;

import org.junit.jupiter.api.Test;

import java.util.*;
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

    @Test
    void test3() {
        // 方式1: 直接对比两个集合差异
        List<String> list0 = getList(0); // ["a","b"]
        List<String> list1 = getList(1); // ["a","c"]

        // 找出互相不存在的元素（差集）
        List<String> difference = Stream.concat(
                list0.stream().filter(e -> !list1.contains(e)),
                list1.stream().filter(e -> !list0.contains(e))
        ).collect(Collectors.toList());

        System.out.println("差异元素: " + difference); // 输出 [b, c]

        // 方式2: 分组统计差异（更直观）
        Map<Boolean, List<String>> partitioned = Stream.concat(list0.stream(), list1.stream())
                .distinct()
                .collect(Collectors.partitioningBy(e -> list0.contains(e) && list1.contains(e)));

        List<String> commonElements = partitioned.get(true);  // 交集 ["a"]
        List<String> uniqueElements = partitioned.get(false); // 差集 ["b","c"]

        System.out.println("commonElements = " + commonElements);
        System.out.println("uniqueElements = " + uniqueElements);
    }


    List<String> getList(int i) {
        if (i == 0) {
            return List.of("a", "b");
        } else if (i == 1) {
            return List.of("a", "c");
        }
        return null;
    }
}
