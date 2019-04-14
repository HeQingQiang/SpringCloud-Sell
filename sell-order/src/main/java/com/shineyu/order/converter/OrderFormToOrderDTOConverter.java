package com.shineyu.order.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.shineyu.order.dataobject.OrderDetail;
import com.shineyu.order.dto.OrderDTO;
import com.shineyu.order.enums.ResultEnum;
import com.shineyu.order.exception.OrderException;
import com.shineyu.order.formdata.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象转换类
 *
 * @author shineYu
 * @Date 19-3-5 下午2:42
 */
@Slf4j
public class OrderFormToOrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Gson gson = new Gson();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            log.error("【json转换】错误，String={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
