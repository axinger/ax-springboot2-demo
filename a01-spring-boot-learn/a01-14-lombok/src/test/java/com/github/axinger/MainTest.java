package com.github.axinger;


import lombok.NonNull;
import org.junit.Test;

public class MainTest {

    public void getName(@NonNull String name) {

        System.out.println("name = " + name);
    }

    @Test
    public void test() {


        getName(null);
    }
}
