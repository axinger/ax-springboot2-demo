package com.axing.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEntity<T extends Model<T>> extends Model<T> {

    @TableId(type = IdType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)//INSERT代表只在插入时填充
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)// INSERT_UPDATE 首次插入、其次更新时填充(或修改)
    private Date updateTime;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();
}
