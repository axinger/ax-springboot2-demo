package com.github.axinger;

import lombok.AllArgsConstructor;
import lombok.Getter;

//定义枚举
@Getter
@AllArgsConstructor
public enum Gender {
    MALE("1", "男性"),
    FEMALE("2", "女性"),
    UNKNOWN("3", "未知");

    private final String code;
    private final String alias;

}
