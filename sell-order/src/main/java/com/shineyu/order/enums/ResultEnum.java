package com.shineyu.order.enums;

import lombok.Getter;

/**
 * 消息提示枚举
 *
 * @author shineYu
 * @Date 19-3-5 下午2:38
 */
@Getter
public enum ResultEnum {


    PARAM_ERROR(100, "参数错误"),
    CART_EMPTY(200, "购物车信息为空");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
