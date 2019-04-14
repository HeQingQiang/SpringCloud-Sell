package com.shineyu.product.service;

import com.shineyu.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author shineYu
 * @Date 19-3-5 上午10:24
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
