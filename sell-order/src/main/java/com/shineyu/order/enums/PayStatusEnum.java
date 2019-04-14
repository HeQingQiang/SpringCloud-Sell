package com.shineyu.order.enums;

import lombok.Getter;

/**
 * 订单支付状态
 *
 * @author shineYu
 * @Date 19-3-4 下午9:39
 */
@Getter
public enum PayStatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
