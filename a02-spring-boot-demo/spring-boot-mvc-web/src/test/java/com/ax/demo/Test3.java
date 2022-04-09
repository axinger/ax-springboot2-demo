package com.ax.demo;

import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test3 {

    @Test
    void test() throws IOException {

//        Resource resource = new ClassPathResource("");
//        System.out.println(resource.getFile().getAbsolutePath());
//
//        String dir = System.getProperty("user.dir");
//        System.out.println("输出目录 = " + dir);

        Properties props = System.getProperties();
        props.list(System.out);

        props.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });


    }


}
