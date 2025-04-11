package com.github.axinger.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class BuilderExample {

    @Default
    private int age = 1;

    //@Singular 是 Java 中用于注解的标记，通常与 Google 的 AutoValue 或 Immutables 库一起使用。它的功能是标记一个集合类型的字段（如 List、Set 等），以便在生成构造器或方法时能够支持更简洁的添加操作。
    // 例如，标注为 @Singular 的字段可以通过多次调用 add 方法来添加元素，而不需要直接操作集合。
    @Singular
    private List<String> fingers;

    @Builder
    public BuilderExample(int age, List<String> fingers) {
        this.age = age;
        this.fingers = fingers;
    }

    public static void main(String[] args) {
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
        build2.fingers.add("1");
//
//        BuilderExample example = new BuilderExample();
//        example.fingers.add("1");
//        System.out.println("example = " + example);
    }
}
