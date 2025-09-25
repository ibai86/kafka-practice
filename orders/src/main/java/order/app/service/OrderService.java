package order.app.service;

import lombok.RequiredArgsConstructor;
import order.app.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;


}
