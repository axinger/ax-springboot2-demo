package com.ax.shop.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * t_student
 * @author 
 */
@Data
@Builder
public class TStudent implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 0保密，1男，2女
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    private static final long serialVersionUID = 1L;
}