package xyz.ax.demo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * t_storage
 *
 * @author
 */
@Data
@ToString
public class TStorage implements Serializable {
    private static final long serialVersionUID = 1L;
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
}