package com.shineyu.product.enums;

import lombok.Getter;

/**
 * 商品上下架状态
 *
 * @author shineYu
 * @Date 19-3-4 下午9:39
 */
@Getter
public enum ProductStatusEnum {

    UP(1, "在架"),
    DOWN(0, "下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
