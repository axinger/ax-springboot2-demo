package com.github.axinger;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ObjectsTests {

    @Test
    void test1() {
        String imgUrl = null;
        boolean startsWith = Objects.requireNonNull(imgUrl).startsWith("http");
        System.out.println("startsWith = " + startsWith);
    }
}
