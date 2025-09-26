package order.app.dto;

import java.util.UUID;

public record OrderEvent(
        UUID oderId,
        Long customerId,
        String product,
        int quantity
) {
}
