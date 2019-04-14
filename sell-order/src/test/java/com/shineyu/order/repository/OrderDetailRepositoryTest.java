package com.shineyu.order.repository;

import com.shineyu.order.OrderApplicationTests;
import com.shineyu.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author shineYu
 * @Date 19-3-5 下午1:48
 */
@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("OD20190305001");
        orderDetail.setOrderId("DM20190305001");
        orderDetail.setProductIcon("https://fuss10.elemecdn.com/e/51/eb0bb1ae246e5518e9899fc4efb67jpeg.jpeg?imageMogr2/thumbnail/100x100/format/webp/quality/85");
        orderDetail.setProductId("GCYX10001");
        orderDetail.setProductName("蜜柑");
        orderDetail.setProductPrice(new BigDecimal(52013.14));
        orderDetail.setProductQuantity(1);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result != null);
    }

}