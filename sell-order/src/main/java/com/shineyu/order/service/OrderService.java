package com.shineyu.order.service;

import com.shineyu.order.dto.OrderDTO;

/**
 * 订单接口
 *
 * @author shineYu
 * @Date 19-3-5 下午2:02
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

}
