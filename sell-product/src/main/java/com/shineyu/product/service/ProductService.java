package com.shineyu.product.service;

import com.shineyu.product.dto.CartDTO;
import com.shineyu.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @author shineYu
 * @Date 19-3-4 下午9:32
 */
public interface ProductService {

    /**
     * 查询所有在架的商品
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表（供order调用）
     *
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     *扣库存（供order调用）
     *
     * @param cartDTOList 接受对象数组
     */
    void decreaseStock(List<CartDTO> cartDTOList);

}
