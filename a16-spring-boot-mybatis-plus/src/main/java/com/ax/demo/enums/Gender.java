package com.ax.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xing
 * @date 2022/3/26 19:23
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum Gender {
    /**
     *
     */
    unknown(0, "未知"),
    male(1, "男"),
    female(2, "女");

    /**
     * 在数据库需要存储的属性上添加 @EnumValue 注解,灵活一点
     */
    /**
     * 数据库字段
     */
    @EnumValue
    private final int code;
    /**
     * 映射成结果
     */
    @JsonValue
    private final String value;

    /**
     * 用来映射，需要手动重写！可不写,就是打印看方便
     *
     * @return
     */
    @Override
    public String toString() {
        return this.value;
    }
}
