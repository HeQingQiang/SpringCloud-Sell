package com.shineyu.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 消费方
 *
 * @author shineYu
 * @Date 19-3-19 下午10:05
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    //@StreamListener(StreamClient.INPUT)
    //public void process(Object message){
    //    log.info("StreamReceiver: {}", message);
    //}

}
