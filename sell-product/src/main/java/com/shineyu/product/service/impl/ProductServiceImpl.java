package com.shineyu.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.tools.json.JSONUtil;
import com.shineyu.product.dto.CartDTO;
import com.shineyu.product.dataobject.ProductInfo;
import com.shineyu.product.enums.ProductStatusEnum;
import com.shineyu.product.enums.ResultEnum;
import com.shineyu.product.exception.ProductException;
import com.shineyu.product.repository.ProductInfoRepository;
import com.shineyu.product.service.ProductService;
import com.shineyu.product.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author shineYu
 * @Date 19-3-4 下午9:36
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findAllByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findAllByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            log.info("\n<查询商品详细信息>========={}===========>", DateUtil.getNowDateTime());
            Optional<ProductInfo> optional = productInfoRepository.findById(cartDTO.getProductId());

            //判断商品是否存在
            if (!optional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = optional.get();
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            //判断库存是否足够
            if (result<0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            //保存改变后的库存
            productInfo.setProductStock(result);
            log.info("\n<修改商品库存信息>========={}===========>", DateUtil.getNowDateTime());
            productInfoRepository.save(productInfo);

            //发送MQ消息
            amqpTemplate.convertAndSend("productInfo", JSON.toJSONString(productInfo));
        }
    }

}
