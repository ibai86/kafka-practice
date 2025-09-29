package com.store.payment.service;

import com.store.payment.config.OrderServiceClient;
import com.store.payment.dto.OrderEvent;
import com.store.payment.dto.OrderStatus;
import com.store.payment.exception.OrderPaymentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessingService {
    private final OrderServiceClient orderServiceClient;
    private final KafkaProducerService kafkaProducerService;
    private final PaymentServiceHandle paymentServiceHandle;

    @Transactional
    public void processCreatedOrder(OrderEvent dto) {
        try {
            paymentServiceHandle.payCreatedOrder(dto);
            orderServiceClient.updateOrderStatus(dto.orderId(), OrderStatus.PAYED);

            log.info("Order #{} ready to shipping", dto.orderId());
            kafkaProducerService.sendOrderPayment(dto);

        } catch (Exception ex) {
            throw new OrderPaymentException(String.format("Order processing error cause of %s", ex.getMessage()));
        }
    }
}
