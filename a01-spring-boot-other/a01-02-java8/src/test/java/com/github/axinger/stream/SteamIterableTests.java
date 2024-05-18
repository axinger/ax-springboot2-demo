package com.github.axinger.stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class SteamIterableTests {

    @Test
    void test1() {
        Iterable<String> iterable
                = Arrays.asList("Testing", "Iterable", "conversion", "to", "Stream");
        List<String> result = StreamSupport.stream(iterable.spliterator(), false)
                .map(String::toUpperCase)
                .toList();
        System.out.println("result = " + result);

        List<String> result2 = StreamSupport.stream(iterable.spliterator(), false)
                .map(String::toLowerCase)
                .toList();
        System.out.println("result2 = " + result2);
    }
}
