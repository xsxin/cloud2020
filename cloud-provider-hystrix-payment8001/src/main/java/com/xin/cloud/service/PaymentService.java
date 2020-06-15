package com.xin.cloud.service;

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
     * 暂停三秒继续执行
     * @param id
     * @return
     */
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + "  paymentInfo_Timeout" + id + "O(∩_∩)O哈哈~" + "耗时(秒)：" + timeNumber;
    }
}
