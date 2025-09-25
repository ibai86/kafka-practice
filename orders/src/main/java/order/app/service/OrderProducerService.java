package order.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.app.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducerService {

    @Value("${kafka.orders.topic-name}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderEvent(UUID orderId, Long customerId) {
        OrderEvent message = new OrderEvent(orderId, customerId);

        kafkaTemplate.send(topicName, String.valueOf(orderId), message);
        log.info("New order #{} awaiting payment", orderId);
    }
}
