package com.xin.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: xsxin
 * @Date: 2020/6/15 21:48
 */
@Service
public class PaymentService {

    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "  paymentInfo_OK" + id + "O(∩_∩)O哈哈~";
    }

    /**
     * 暂停三秒继续执行。
     * @HystrixCommand服务降级调用paymentInfo_TimeoutHandler()方法.
     * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")设置方法响应时间3秒，超时调用paymentInfo_TimeoutHandler()
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) {
//        int timeNumber = 5;
        int age = 10/0;
//        try {
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_Timeout 方法 :"+ id +"O(∩_∩)O哈哈~" + "耗时(秒)：";
    }

    /**
     * @HystrixCommand注解指定方法。运行异常或者超时异常返回提示
     */
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  运行异常或者超时异常，请稍后再试o(╥﹏╥)o";
    }
}
