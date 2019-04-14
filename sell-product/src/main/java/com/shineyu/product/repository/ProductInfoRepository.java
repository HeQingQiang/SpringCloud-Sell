package com.shineyu.product.repository;

import com.shineyu.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品信息接口
 * @author shineYu
 * @Date 19-3-4 下午8:38
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    //查询所有在架商品
    List<ProductInfo> findAllByProductStatus(Integer productStatus);

    //通过ID查询所有在架商品
    List<ProductInfo> findAllByProductIdIn(List<String> productIdList);

}
