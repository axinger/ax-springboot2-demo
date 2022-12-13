package com.axing.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * t_student
 *
 * @author
 */
@Data
@Builder
public class TStudent implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
