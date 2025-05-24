package com.github.axinger.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName department
 */
@TableName(value ="ax_department")
@Data
public class DepartmentEntity implements Serializable {
    /**
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     *
     */
    @TableField(value = "name")
    private String name;

    /**
     *
     */
    @TableField(value = "dept_leader_id")
    private String deptLeaderId;

    /**
     *
     */
    @TableField(value = "company_leader_id")
    private String companyLeaderId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
