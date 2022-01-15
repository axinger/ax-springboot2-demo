package xyz.ax.demo.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_order
 *
 * @author
 */
@Data
@ToString
public class TOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 库存数量
     */
    private Integer count;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 0创建中,1完结
     */
    private Integer status;
}