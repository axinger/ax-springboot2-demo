package com.github.axinger;

import com.github.axinger.model.BuilderExample;
import org.junit.Test;

import java.util.ArrayList;

public class BuilderTests {


    @Test
    public void test1() {
        BuilderExample build1 = BuilderExample.builder()
                .fingers(new ArrayList<>())
                .build();
        System.out.println("build1 = " + build1);

        BuilderExample build2 = BuilderExample.builder()
//                .finger("小拇指")
//                .finger("食指")
//                .clearFingers()
                .build();
        System.out.println("build2 = " + build2);
//        build2.getFingers().add("1");
//
//        BuilderExample example = new BuilderExample();
//        example.fingers.add("1");
//        System.out.println("example = " + example);
    }
}
