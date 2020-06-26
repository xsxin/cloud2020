package com.xin.cloud.controller;

import com.xin.cloud.entities.CommonResult;
import com.xin.cloud.entities.Payment;
import com.xin.cloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author xsx
 */
@RestController
public class OrderController {

    // 使用eureka提供的微服务名称
    private static final String PAYMENY_URL = "http://CLOUD-PATMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

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
     * 使用手写负载均衡算法执行程序
     * @return
     */
    @GetMapping("/consumer/payment/lb")
    public String getPaymenyLB() {
        // 获取注册中心中存在的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PATMENT-SERVICE");
        if (instances == null || instances.size() <= 0)
        {
            return null;
        }
        // 通过cas和自旋锁获取实例
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();

        return restTemplate.getForObject(uri + "/payment/lb", String.class);
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

    /**
     * 验证sleuth+zipkin监控链路
     * @return
     */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipKin() {
        String forObject = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin", String.class);
        return forObject;
    }
}
