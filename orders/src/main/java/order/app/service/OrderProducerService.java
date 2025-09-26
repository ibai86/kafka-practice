package order.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.app.dto.OrderEvent;
import order.app.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducerService {

    @Value("${kafka.orders.topic-name}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderEvent(Order order, Long customerId) {
        OrderEvent message = new OrderEvent(order.getId(), customerId, order.getProduct(), order.getQuantity());

        kafkaTemplate.send(topicName, String.valueOf(order.getId()), message);
        log.info("New order #{} awaiting payment", order.getId());
    }
}
