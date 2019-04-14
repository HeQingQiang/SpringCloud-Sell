package com.shineyu.order.service.impl;

import com.shineyu.order.client.ProductClient;
import com.shineyu.order.dataobject.OrderDetail;
import com.shineyu.order.dataobject.OrderMaster;
import com.shineyu.order.dataobject.ProductInfo;
import com.shineyu.order.dto.CartDTO;
import com.shineyu.order.dto.OrderDTO;
import com.shineyu.order.enums.OrderStatusEnum;
import com.shineyu.order.enums.PayStatusEnum;
import com.shineyu.order.repository.OrderDetailRepository;
import com.shineyu.order.repository.OrderMasterRepository;
import com.shineyu.order.service.OrderService;
import com.shineyu.order.utils.DateUtil;
import com.shineyu.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shineYu
 * @Date 19-3-5 下午2:08
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //生成订单主键
        String orderId = KeyUtil.genUniqueKeyByOrderMaster();

        //查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList()
                .stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());

        log.info("\n<订单调用商品服务>========={}===========>", DateUtil.getNowDateTime());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKeyByOrderDetail());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
            //单价*数量
        }
        log.info("\n<保存订单详细信息>========={}===========>", DateUtil.getNowDateTime());

        //扣库存（调用商品服务）
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        log.info("\n<修改商品库存信息>========={}===========>", DateUtil.getNowDateTime());
        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        log.info("\n<保存订单主体信息>========={}===========>", DateUtil.getNowDateTime());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

}
