package com.ax.demo;


import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("测试类...")
public class Test2 {


    @BeforeAll
    static void beforeAll() {
        System.out.println("Test2.beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Test2.afterAll");
    }

    @DisplayName("测试类-test1")
    @Test
    void test1() {
        System.out.println("Test2.test1");

        assertEquals(1, 1);

    }

    @DisplayName("测试类-test2")
    @Test
    @Timeout(value = 2, unit = TimeUnit.MILLISECONDS)
    void test2() throws InterruptedException {
        System.out.println("Test2.test2");
        Thread.sleep(600);
    }

    @DisplayName("测试类-test2")
    @Test
    @Disabled
    void test3() {
        System.out.println("Test2.test3");
    }

    @DisplayName("测试类-test4")
    @Test
    @RepeatedTest(5)
    void test4() {
        System.out.println("Test2.test4");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Test2.beforeEach");

    }

    @AfterEach
    void afterEach() {
        System.out.println("Test2.afterEach");

    }
}
