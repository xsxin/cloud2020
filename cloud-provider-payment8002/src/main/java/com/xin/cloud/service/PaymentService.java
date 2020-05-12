package com.xin.cloud.service;

import com.xin.cloud.entities.Payment;

/**
 * @author xsx
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
