package order.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.app.dto.OrderResponseDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducerService {

    private final KafkaTemplate<String, OrderResponseDto> kafkaTemplate;

    public void sendOrderEvent(OrderResponseDto dto) {
        UUID id = dto.id();
        if (id == null) {
            throw new IllegalArgumentException("Order id not found");
        }

        kafkaTemplate.send("${kafka.orders.topic-name}", id.toString(), dto);
        log.info("New order #{} awaiting payment", id);
    }
}
