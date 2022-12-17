package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName t_room
 */
@TableName(value ="t_room")
@Data
public class Room implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private String layer;

    /**
     *
     */
    private String roomName;

    /**
     *
     */
    private Long schoolId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
