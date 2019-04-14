package com.shineyu.order.client;

import com.shineyu.order.common.ServerResponse;
import com.shineyu.order.dataobject.ProductInfo;
import com.shineyu.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 远程调用接口
 *
 * @author shineYu
 * @Date 19-3-5 下午5:08
 */
@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
     void decreaseStock(@RequestBody List<CartDTO> cartDTOList);

}
