package order.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.app.dto.OrderRequestDto;
import order.app.dto.OrderResponseDto;
import order.app.model.Order;
import order.app.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository repository;
    private final OrderProducerService orderProducerService;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Order newOrder = Order.builder()
                .product(dto.product())
                .quantity(dto.quantity())
                .orderStatus(Order.OrderStatus.CREATED)
                .build();

        Order savedOrder = repository.save(newOrder);
        log.info("New order #{} successfully created", savedOrder.getId());

        OrderResponseDto newOrderDto = OrderResponseDto.toDto(savedOrder);
        orderProducerService.sendOrderEvent(newOrderDto);

        return newOrderDto;
    }
}
