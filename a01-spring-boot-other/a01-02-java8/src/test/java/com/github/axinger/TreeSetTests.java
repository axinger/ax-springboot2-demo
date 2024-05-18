package com.github.axinger;

import org.junit.jupiter.api.Test;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeSetTests {

    @Test
    void test1() {
        TreeSet<String> set = new TreeSet<>();
        set.add("2");
        set.add("1");
        set.add("10");
        System.out.println("set = " + set);

        TreeSet<Integer> set1 = set.stream().map(Integer::valueOf).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("set1 = " + set1);
    }


    @Test
    void test2() {


    }
}
