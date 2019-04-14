package com.shineyu.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author shineYu
 * @Date 19-3-19 下午9:53
 */
public interface StreamClient {

    String INPUT = "myMessageInput";
    String OUTPUT = "myMessageOutput";

    @Input(StreamClient.OUTPUT)
    SubscribableChannel input();

    @Output(StreamClient.INPUT)
    MessageChannel output();

}
