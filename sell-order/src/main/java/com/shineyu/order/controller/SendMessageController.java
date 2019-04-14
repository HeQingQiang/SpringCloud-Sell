package com.shineyu.order.controller;

import com.shineyu.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author shineYu
 * @Date 19-3-19 下午10:07
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
        streamClient.output().send(
                MessageBuilder.withPayload("now " + new Date()).build());
    }

}
