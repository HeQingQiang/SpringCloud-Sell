package com.shineyu.product.exception;

import com.shineyu.product.enums.ResultEnum;

/**
 * @author shine95
 * @Date 19-3-17 下午11:11
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
