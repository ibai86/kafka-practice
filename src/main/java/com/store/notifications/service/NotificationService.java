package com.store.notifications.service;

import com.store.notifications.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public void sendNotificationToCustomer(OrderEvent dto) {
        log.info("Order #{}, product: {}, quantity: {} successfully sent to customer #{}",
                            dto.orderId(), dto.product(), dto.quantity(), dto.customerId());
    }
}
