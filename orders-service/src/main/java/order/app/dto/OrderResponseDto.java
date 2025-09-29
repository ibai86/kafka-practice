package order.app.dto;

import order.app.model.Order;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        String product,
        int quantity,
        Order.OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getProduct(),
                order.getQuantity(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
