package com.store.notifications.service;

import com.store.notifications.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "${kafka.shipping.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenOrderEvent(OrderEvent dto) {

        notificationService.sendNotificationToCustomer(dto);
    }
}
