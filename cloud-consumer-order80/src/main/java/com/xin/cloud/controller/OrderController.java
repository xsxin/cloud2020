package com.xin.cloud.controller;

import com.xin.cloud.entities.CommonResult;
import com.xin.cloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xsx
 */
@RestController
public class OrderController {

    // 使用eureka提供的微服务名称
    private static final String PAYMENY_URL = "http://CLOUD-PATMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENY_URL + "/payment/create", payment, CommonResult.class);
    }

    /**
     * restTemplate.getForObject方法返回对象为响应体中数据转化成的对象，基本上可以理解为json数据格式
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENY_URL + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * restTemplate.getForEntity方法返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENY_URL + "/payment/get/" + id, CommonResult.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            return forEntity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }
}
