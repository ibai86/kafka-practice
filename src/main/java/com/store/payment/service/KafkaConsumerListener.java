package com.store.payment.service;

import com.store.payment.dto.OrderEvent;
import com.store.payment.exception.OrderPaymentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerListener {

    private final ProcessingService processingService;

    @KafkaListener(topics = "${kafka.orders.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrderEvent(OrderEvent dto) {
        if (dto == null) {
            throw new OrderPaymentException("OrderEvent message is empty");
        }

        processingService.processCreatedOrder(dto);
    }
}
