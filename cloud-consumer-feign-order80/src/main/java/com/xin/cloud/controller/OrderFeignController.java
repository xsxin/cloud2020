package com.xin.cloud.controller;

import com.xin.cloud.entities.CommonResult;
import com.xin.cloud.entities.Payment;
import com.xin.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xsxin
 * @Date: 2020/6/10 21:44
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    /**
     * 测试openfeign服务调用
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 测试openFeign的超时控制
     * @return
     */
    @GetMapping("/consumer/payment/feign/timeout")
    private String paymentFeignTimeout() {
        // opennFeign-ribbon客户端默认等待一秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
