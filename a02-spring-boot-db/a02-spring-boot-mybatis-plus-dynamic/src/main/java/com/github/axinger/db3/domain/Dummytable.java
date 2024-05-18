package com.github.axinger.db3.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName dummytable
 */
@TableName(value = "dummytable")
@Data
public class Dummytable implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableField(value = "id")
    private Object id;
    /**
     *
     */
    @TableField(value = "name")
    private String name;
}
