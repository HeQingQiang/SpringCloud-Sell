package com.shineyu.order.repository;

import com.shineyu.order.OrderApplicationTests;
import com.shineyu.order.dataobject.OrderMaster;
import com.shineyu.order.enums.OrderStatusEnum;
import com.shineyu.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author shineYu
 * @Date 19-3-5 上午11:47
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("DD20190305001");
        orderMaster.setBuyerName("shineYu_颜恒");
        orderMaster.setBuyerPhone("18510644644");
        orderMaster.setBuyerAddress("重庆市渝北区金开协信4栋20-04");
        orderMaster.setBuyerOpenid("shine5201314Yu");
        orderMaster.setOrderAmount(new BigDecimal(52013.14));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result!=null);
    }

}