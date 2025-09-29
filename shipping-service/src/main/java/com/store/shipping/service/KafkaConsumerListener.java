package com.store.shipping.service;

import com.store.shipping.dto.OrderEvent;
import com.store.shipping.exception.OrderShippingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerListener {

    private final ShippingService shippingService;

    @KafkaListener(topics = "${kafka.payment.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrderEvent(OrderEvent dto) {
        if (dto == null) {
            throw new OrderShippingException("OrderEvent message is empty");
        }

        shippingService.processPayedOrder(dto);
    }

}
