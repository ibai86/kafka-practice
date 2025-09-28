package com.store.payment.dto;

import java.util.UUID;

public record OrderPaymentEvent(
        UUID orderId,
        Long customerId,
        String product,
        int quantity,
        OrderStatus status
) {
}
