package com.shineyu.order.exception;

import com.shineyu.order.enums.ResultEnum;

/**
 * 订单异常处理类
 *
 * @author shineYu
 * @Date 19-3-5 下午2:32
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
