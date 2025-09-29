package com.store.shipping.service;

import com.store.shipping.config.OrderServiceClient;
import com.store.shipping.dto.OrderEvent;
import com.store.shipping.dto.OrderStatus;
import com.store.shipping.exception.OrderShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingService {

    private final OrderServiceClient orderServiceClient;
    private final KafkaProducerService kafkaProducerService;

    @Transactional
    public void processPayedOrder(OrderEvent dto) {
        try {
            log.info("Order #{} is being processed............", dto.orderId());

            orderServiceClient.updateOrderStatus(dto.orderId(), OrderStatus.SENT);
            log.info("Order #{} successfully sent: product: {}, quantity: {}", dto.orderId(), dto.product(), dto.quantity());

            kafkaProducerService.sendOrderShipping(dto);

        } catch (Exception ex) {
            throw new OrderShippingException(String.format("Order shipping error cause of %s", ex.getMessage()));
        }
    }
}
