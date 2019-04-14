package com.shineyu.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author shineYu
 * @Date 19-3-5 上午10:24
 */
@Data
public class ProductVO {


    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<ProductInfoVO> productInfoVOList;

}
