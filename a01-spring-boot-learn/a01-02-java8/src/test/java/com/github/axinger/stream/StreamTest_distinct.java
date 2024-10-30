package com.github.axinger.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 流也可以进行合并、去重、限制、跳过等操作。
public class StreamTest_distinct {
    public static void main(String[] args) {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct 清楚的：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        System.out.println("流合并去重 distinct：" + newList);


        // limit：限制从流中获得前n个数据
        System.out.println("Stream.iterate(1, x -> x + 2) = " + Stream.iterate(1, x -> x + 2));

        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        System.out.println("limit：" + collect);

        List<Integer> collect1 = Stream.iterate(1, x -> x + 1).limit(10).collect(Collectors.toList());
        System.out.println("limit collect1：" + collect1);

        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(3).limit(5).collect(Collectors.toList());
        System.out.println("skip：" + collect2);
    }
}
