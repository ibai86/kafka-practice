package com.store.shipping.service;

import com.store.shipping.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.shipping.topic-name}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderPayment(OrderEvent message) {

        kafkaTemplate.send(topicName, String.valueOf(message.orderId()), message);
    }
}
