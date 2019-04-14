package com.shineyu.order.message;

import com.alibaba.fastjson.JSON;
import com.shineyu.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author shineYu
 * @Date 19-3-19 下午11:41
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message ){
        //message转成productInfo
        ProductInfo productInfo = JSON.parseObject(message, ProductInfo.class);
        log.info("从队列【{}】接收到消息：{}","productInfo", productInfo);

        //存储到Redis
        redisTemplate.opsForValue().set(
                String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()),
                String.valueOf(productInfo.getProductStock()));
    }

}
