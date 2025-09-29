package order.app.dto;

import order.app.model.Order;

import java.util.UUID;

public record OrderRequestDto(
        UUID id,
        String product,
        int quantity,
        Order.OrderStatus status
) {
}
