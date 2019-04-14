package com.shineyu.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shineYu
 * @Date 19-3-5 下午3:53
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg(){
        return "this is a message";
    }

}
