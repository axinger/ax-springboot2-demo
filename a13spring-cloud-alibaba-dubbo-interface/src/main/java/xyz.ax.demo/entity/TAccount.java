package xyz.ax.demo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_account
 *
 * @author
 */
@Data
@ToString
public class TAccount implements Serializable {
    private static final long serialVersionUID = 1L;
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
}