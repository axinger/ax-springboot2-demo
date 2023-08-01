package com.axing.demo;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    @Test
    public void tes1() throws IOException {



        HashMap<Object, Object> hashMap = new HashMap<>();//
        hashMap.put("d", "d");
        hashMap.put("b", "b");
        hashMap.put("a", "a");
        hashMap.forEach((key, val) -> {
//            System.out.println("hashMap key = " + key +" val = " +val );
            System.out.println(" val = " + val);
        });

        hashMap.entrySet().stream().forEach((val) -> {
            System.out.println(" stream val = " + val);
        });


        //默认值
        hashMap.getOrDefault("name","test");

        new LinkedHashMap<>();

        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put("d", "d");
        treeMap.put("b", "b");
        treeMap.put("a", "a");
        treeMap.forEach((key, val) -> {
            System.out.println("treeMap key = " + key + " val = " + val);
        });

        // Properties extends Hashtable
        Properties properties = new Properties();
        FileInputStream stream = new FileInputStream("src/jdbc.properties");
        properties.load(stream);
        System.out.println("properties = " + properties);


    }
}
