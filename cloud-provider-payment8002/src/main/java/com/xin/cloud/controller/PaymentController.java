package com.xin.cloud.controller;

import com.xin.cloud.entities.CommonResult;
import com.xin.cloud.entities.Payment;
import com.xin.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author xsx
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("插入结果" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult(444, "插入失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败");
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
