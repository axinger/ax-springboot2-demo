package com.github.axinger.model.bean;

import cn.hutool.core.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Gender {

    none(0, "保密"),
    male(1, "男性"),
    female(2, "女性");


    private int code;

    private String alias;

    @JsonCreator
    public static Gender fromCode(int code) {

//        return Arrays.stream(Gender.values())
//                .filter(s -> s.code == code)
//                .findFirst()
//                .orElse(none);

        return EnumUtil.getBy(Gender::getCode, code, Gender.none);
    }

    //使用@JsonCreator和@JsonValue注解来自定义序列化和反序列化的方式。
    // 这样可以在枚举类中指定一个方法，用来将传入的字符串转换为对应的枚举值
    @JsonValue
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return alias;
    }
}
