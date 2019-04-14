package com.shineyu.order.controller;

import com.shineyu.order.common.ServerResponse;
import com.shineyu.order.converter.OrderFormToOrderDTOConverter;
import com.shineyu.order.dto.OrderDTO;
import com.shineyu.order.enums.ResultEnum;
import com.shineyu.order.exception.OrderException;
import com.shineyu.order.formdata.OrderForm;
import com.shineyu.order.service.OrderService;
import com.shineyu.order.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单控制器
 * @author shineYu
 * @Date 19-3-5 下午1:58
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     */

    @PostMapping("/create")
    public ServerResponse create(@Valid OrderForm orderForm,
                                 BindingResult bindingResult){
        log.info("\n【创建订单信息】========={}===========>",DateUtil.getNowDateTime());
        if (bindingResult.hasErrors()) {
            log.error("【创建订单信息】参数不正确，orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        //orderForm -> orderDTO
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);

        //判断购物车信息是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单信息】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ServerResponse.createBySuccess("创建订单成功",map);

    }

}
