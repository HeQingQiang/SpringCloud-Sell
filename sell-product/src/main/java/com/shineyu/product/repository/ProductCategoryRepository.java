package com.shineyu.product.repository;

import com.shineyu.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author shineYu
 * @Date 19-3-4 下午9:12
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    //2.获取类目type列表
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

}
