package com.axing.demo.db3.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName dummytable
 */
@TableName(value ="dummytable")
@Data
public class Dummytable implements Serializable {
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
