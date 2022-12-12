package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName t_student
 */
@TableName(value ="t_student")
@Data
public class Student implements Serializable {
    private Long id;

    private String name;

    private Integer age;

    private Integer gender;

    private String address;

    private Date createTime;

    private Date updateTime;

    private Long version;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}