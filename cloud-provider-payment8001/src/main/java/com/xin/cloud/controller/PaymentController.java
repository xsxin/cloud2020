package com.xin.cloud.controller;

import com.xin.cloud.entities.CommonResult;
import com.xin.cloud.entities.Payment;
import com.xin.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xsx
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("插入结果" + result);

        if (result > 0) {
            return new CommonResult(200, "插入成功,serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果" + payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败");
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    /**
     * 获取微服务自身信息
     * @return
     */
    @GetMapping("/payment/discovery")
    public Object getDiscovery() {
        // 获取注册到eureka中的服务名称
        List<String> services = discoveryClient.getServices();
        for (String elemnt: services) {
            log.info("******elemnt: " + elemnt);
        }

        //获取eureka中某个微服务名称下的所有服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PATMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info(instance.getServiceId()+ "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    /**
     * 测试openFeign的超时控制，默认暂停3秒钟
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
