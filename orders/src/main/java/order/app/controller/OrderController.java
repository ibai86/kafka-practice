package order.app.controller;

import order.app.dto.OrderRequestDto;
import order.app.dto.OrderResponseDto;
import order.app.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.createOrder(dto));
    }

//    @PutMapping("/{id}/status")
//    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
//        Order updatedOrder = orderService.updateOrderStatus(id, status);
//        if (updatedOrder != null) {
//            return ResponseEntity.ok(updatedOrder);
//        }
//        return ResponseEntity.notFound().build();
//    }
}
