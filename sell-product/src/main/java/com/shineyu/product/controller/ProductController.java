package com.shineyu.product.controller;

import com.shineyu.product.dto.CartDTO;
import com.shineyu.product.common.ServerResponse;
import com.shineyu.product.dataobject.ProductCategory;
import com.shineyu.product.dataobject.ProductInfo;
import com.shineyu.product.service.CategoryService;
import com.shineyu.product.service.ProductService;
import com.shineyu.product.utils.DateUtil;
import com.shineyu.product.vo.ProductInfoVO;
import com.shineyu.product.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品控制器
 *
 * @author shineYu
 * @Date 19-3-4 下午7:54
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    /**
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     */

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ServerResponse list() {
        log.info("\n【获取商品列表信息】========={}===========>", DateUtil.getNowDateTime());

        //1.查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.获取类目type列表
        //lambda表达式把商品id放到一个集合里
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //3.查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4.构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVo = new ProductVO();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                //判断商品类目是否属于当前类目
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVo = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVOList.add(productInfoVo);
                }
            }

            productVo.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVo);
        }
        return ServerResponse.createBySuccess("获取商品成功", productVOList);
    }

    /**
     * 获取商品列表（给订单服务调用）
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        log.info("\n【获取商品列表信息】========={}===========>", DateUtil.getNowDateTime());
        return productService.findList(productIdList);
    }

    /**
     * 减库存（给订单服务调用）
     *
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList) {
        log.info("\n【修改商品库存信息】========={}===========>", DateUtil.getNowDateTime());
        productService.decreaseStock(cartDTOList);
    }

}
