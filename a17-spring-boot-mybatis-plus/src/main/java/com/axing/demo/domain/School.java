package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @TableName t_school
 */
@TableName(value ="t_school")
@Data
public class School implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Double area;

    /**
     *
     */
    private String schoolName;

    /**
     *
     */
    private String zone;

    @TableField(exist = false)
    private List<Room> roomList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
