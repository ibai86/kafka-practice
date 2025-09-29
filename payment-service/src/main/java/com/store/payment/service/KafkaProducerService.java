package com.store.payment.service;

import com.store.payment.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    @Value("${kafka.payment.topic-name}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderPayment(OrderEvent message) {

        kafkaTemplate.send(topicName, String.valueOf(message.orderId()), message);
        log.info("Payment order #{} awaiting shipping", message.orderId());
    }
}
