package com.xin.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author: xsxin
 * @Date: 2020/6/24 18:16
 *
 * @EnableBinding(Sink.class) 管道和exchange绑定在一起
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String port;

    /**
     * 监听mq的消费消息
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者一号 ******》" + message.getPayload() + "\t port:" + port);
    }
}
