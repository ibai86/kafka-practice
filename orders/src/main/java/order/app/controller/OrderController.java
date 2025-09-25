package order.app.controller;

import lombok.RequiredArgsConstructor;
import order.app.dto.OrderRequestDto;
import order.app.dto.OrderResponseDto;
import order.app.model.Order;
import order.app.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto dto,
                                                        @RequestParam("customerId") Long customerId) {

        Order order = orderService.create(dto, customerId);
        return ResponseEntity.ok(OrderResponseDto.toDto(order));
    }
}
