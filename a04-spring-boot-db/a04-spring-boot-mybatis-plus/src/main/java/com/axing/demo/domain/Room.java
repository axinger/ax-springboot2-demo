package com.axing.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName t_room
 */
@Data
@TableName(value = "t_room")
@Entity(name = "t_room")
public class Room implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    /**
     *
     */
    @Column
    private String layer;
    /**
     *
     */
    @Column
    private String roomName;
    /**
     *
     */
    @Column
    private Long schoolId;
}
