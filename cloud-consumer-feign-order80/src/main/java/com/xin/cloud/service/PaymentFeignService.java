package com.xin.cloud.service;

import com.xin.cloud.entities.CommonResult;
import com.xin.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: xsxin
 * @Date: 2020/6/10 21:36
 *
 * @FeignClient(value = "CLOUD-PATMENT-SERVICE") Eureka注册中心注册的服务端名称
 */
@Component
@FeignClient(value = "CLOUD-PATMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
