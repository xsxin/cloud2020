package com.xin.cloud.controller;

import com.xin.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xsxin
 * @Date: 2020/6/15 21:53
 *
 * 介绍hystrix的使用
 * 1.服务降级（fallback）
 *     可能出现的情况：
 *     1.程序运行异常
 *     2.超时
 *     3.服务熔断触发服务降级
 *     4.线程池/信号量打满也会导致服务降级
 * 2.服务熔断（break）
 *     达到最大服务访问后，直接拒绝访问。服务的降级--》进而熔断--》恢复调用链路
 * 3.服务限流（flowlimit）
 *     秒杀高并发等操作。限制请求数量。
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 模拟正常请求
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("result: " + result);
        return result;
    }

    /**
     * 模拟高并发下服务降级
     * @param id
     * @return
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("result: " + result);
        return result;
    }
}
