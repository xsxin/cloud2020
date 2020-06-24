package com.xin.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.xin.cloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @Author: xsxin
 * @Date: 2020/6/24 17:11
 *  实现生产者发送消息到消息中间件
 *  @EnableBinding(Source.class) 指定通道与exchange绑定在一起
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    /**
     * 消息发送管道
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = IdUtil.randomUUID();
        // 发送消息到消息队列
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("******:" + serial);
        return null;
    }
}
