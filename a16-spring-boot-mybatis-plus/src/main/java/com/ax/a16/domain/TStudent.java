package com.ax.a16.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息
 *
 * @TableName t_student
 */
@TableName(value = "t_student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TStudent implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 版本号,乐观锁
     * */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private int version;

    @TableLogic
    private int deleted;

}
