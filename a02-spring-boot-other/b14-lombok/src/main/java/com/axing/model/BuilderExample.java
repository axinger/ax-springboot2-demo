package com.axing.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Builder
@NoArgsConstructor
public class BuilderExample {

    @Default
    private int age = 0;

    @Singular
    private List<String> fingers;

    @Builder
    public BuilderExample(int age, List<String> fingers) {
        this.age = age;
        this.fingers = fingers;
    }

    public static void main(String[] args) {
        BuilderExample.builder()
            .age(0)
            .fingers(new ArrayList<>())
            .build();
        //
        BuilderExample build = BuilderExample.builder()
                .finger("小拇指")
                .clearFingers()
                .build();
        build.fingers.add("1");

        // BuilderExample example = new BuilderExample();
        // example.fingers.add("1");
        // System.out.println("example = " + example);
    }
}
