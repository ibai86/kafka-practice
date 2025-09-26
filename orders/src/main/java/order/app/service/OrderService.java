package order.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order.app.dto.OrderRequestDto;
import order.app.model.Customer;
import order.app.model.Order;
import order.app.repository.CustomerRepository;
import order.app.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository repository;
    private final OrderProducerService orderProducerService;
    private final CustomerRepository customerRepository;

    @Transactional
    public Order create(OrderRequestDto dto, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        Order newOrder = Order.builder()
                .customer(customer)
                .product(dto.product())
                .quantity(dto.quantity())
                .orderStatus(Order.OrderStatus.CREATED)
                .build();

        Order savedOrder = repository.save(newOrder);
        log.info("New order #{} successfully created", savedOrder.getId());

        orderProducerService.sendOrderEvent(savedOrder, customerId);

        return savedOrder;
    }
}
