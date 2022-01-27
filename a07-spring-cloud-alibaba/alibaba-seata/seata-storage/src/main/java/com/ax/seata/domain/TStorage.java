package com.ax.seata.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * t_storage
 * @author 
 */
@Data
public class TStorage implements Serializable {
    private Long id;

    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;

    private static final long serialVersionUID = 1L;
}