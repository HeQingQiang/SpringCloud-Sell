package com.shineyu.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *发送MQ消息测试
 *
 * @author shineYu
 * @Date 19-3-19 下午9:08
 */
@Component
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        amqpTemplate.convertAndSend("Yu","You birthday:"+new Date());
    }

    @Test
    public void sendYu(){
        amqpTemplate.convertAndSend("yangYu","You birthday:"+new Date());
    }


}
