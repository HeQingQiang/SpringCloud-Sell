package com.shineyu.order.enums;

import lombok.Getter;

/**
 * 订单状态
 *
 * @author shineYu
 * @Date 19-3-4 下午9:39
 */
@Getter
public enum OrderStatusEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
