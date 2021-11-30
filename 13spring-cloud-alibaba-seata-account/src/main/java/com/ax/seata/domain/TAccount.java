package com.ax.seata.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * t_account
 * @author 
 */
@Data
public class TAccount implements Serializable {
    private Long id;

    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 已额度
     */
    private BigDecimal used;

    /**
     * 剩余可用额度
     */
    private BigDecimal residue;

    private static final long serialVersionUID = 1L;
}