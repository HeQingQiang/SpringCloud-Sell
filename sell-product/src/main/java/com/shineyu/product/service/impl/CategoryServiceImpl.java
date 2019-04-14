package com.shineyu.product.service.impl;

import com.shineyu.product.dataobject.ProductCategory;
import com.shineyu.product.repository.ProductCategoryRepository;
import com.shineyu.product.service.CategoryService;
import com.shineyu.product.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shineYu
 * @Date 19-3-5 上午10:26
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        log.info("\n<查询类目列表信息>========={}===========>", DateUtil.getNowDateTime());
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

}
