package com.store.payment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        String product,
        int quantity,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
