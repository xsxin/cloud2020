package com.xin.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xsx
 *
 * 配置feign的日志级别
 * 1.NONE。默认的，不显示任何日志
 * 2.BASIC。仅记录请求方法、URL、    响应状态码及执行时间
 * 3.HEADERS。除了BASIC记录的信息外，还有请求和响应的头信息
 * 4.FULL。 除了HEADERS记录的信息外，还包括请求和响应的正文及原信息
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
