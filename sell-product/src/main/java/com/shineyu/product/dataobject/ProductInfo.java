package com.shineyu.product.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import java.math.BigDecimal;

/**
 *商品信息实体表
 *
 * @author shineYu
 * @Date 19-3-4 下午8:07
 */
@Data
@Entity
public class ProductInfo {

    /** 主键. */
    @Id
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;

}
