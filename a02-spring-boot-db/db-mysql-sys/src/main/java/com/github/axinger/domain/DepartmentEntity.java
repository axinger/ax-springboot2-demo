package com.github.axinger.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @TableName department
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "department")
public class DepartmentEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /**
     *
     */
    @TableField(value = "name")
    private String name;


    @TableField(exist = false)
    private List<EmployeeEntity> empList;
}
