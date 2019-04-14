package com.shineyu.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受MQ消息
 *
 * @author shineYu
 * @Date 19-3-19 下午9:05
 */
@Slf4j
@Component
public class MqReceiver {

    //1.@RabbitListener(queues = "Yu")
    //2.自动创建队列    @RabbitListener(queuesToDeclare = @Queue("Yu"))
    //3.自动创建，Exchange和Queue绑定|
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("Yu"),
            exchange = @Exchange("myYu")
    ))
    public void getMq(String message){
        log.info("RabbitMQ==========>"+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("Yu"),
            key = "yang",
            value = @Queue("yangYu")
    ))
    public void hello(String message){
        log.info("yangYu RabbitMQ==========>"+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("Yu"),
            key = "fish",
            value = @Queue("fishYu")
    ))
    public void look(String message){
        log.info("fishYu RabbitMQ==========>"+message);
    }

}
