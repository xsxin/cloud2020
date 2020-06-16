package com.xin.cloud.service;

import org.springframework.stereotype.Component;

/**
 * 重写PaymentHystrixService接口，实现Hystrix服务降级
 * @author xsx
 */
@Component
public class PaymentFallBackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK O(∩_∩)O哈哈~";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "paymentInfo_timeout o(╥﹏╥)o";
    }
}
