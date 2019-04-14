package com.shineyu.order.dto;

import lombok.Data;

/**
 * @author shine95
 * @Date 19-3-17 下午10:59
 */
@Data
public class CartDTO {

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

}
