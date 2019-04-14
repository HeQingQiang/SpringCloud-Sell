package com.shineyu.order.controller;

import com.shineyu.order.client.ProductClient;
import com.shineyu.order.common.ServerResponse;
import com.shineyu.order.dataobject.ProductInfo;
import com.shineyu.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author shineYu
 * @Date 19-3-5 下午3:57
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getMsg")
    public String getMsg(){

        //第1种(使用restTemplate硬编码写死URL)
        //RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject("http://localhost:8081/msg", String.class);

        //第2种(使用loadBalancerClient获取应用名拼接参数)
        //RestTemplate restTemplate = new RestTemplate();
        //ServiceInstance product = loadBalancerClient.choose("PRODUCT");
        //String url = String.format("http://%s:%s", product.getHost(), product.getPort()) + "/msg";
        //String result = restTemplate.getForObject(url, String.class);

        //第3种(使用@LoadBalanced获取应用名拼接参数)
        //String result = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        String result = productClient.productMsg();

        log.info("result = {}", result);
        return result;
    }

    @GetMapping("/getProductList")
    public ServerResponse getProductList(){

        List<ProductInfo> result = productClient.listForOrder(Arrays.asList("100001"));

        log.info("result = {}", result);
        return ServerResponse.createBySuccess("获取商品列表成功",result);
    }

    @GetMapping("/productDecreaseStock")
    public ServerResponse decreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDTO("100001", 5)));
        return ServerResponse.createBySuccessMessage("商品减库存成功");
    }

}
