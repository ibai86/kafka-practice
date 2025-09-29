package com.store.payment.service;

import com.store.payment.dto.OrderEvent;
import com.store.payment.exception.OrderPaymentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceHandle {

    public void payCreatedOrder(OrderEvent dto) {
        try {
            Thread.sleep((long) (Math.random() * 1000));
            log.info("Order #{} successfully payed", dto.orderId());
        } catch (InterruptedException e) {
            throw new OrderPaymentException(String.format("Failed to pay order #%s", dto.orderId()));
        }
    }
}
