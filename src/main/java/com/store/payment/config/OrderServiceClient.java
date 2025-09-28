package com.store.payment.config;

import com.store.payment.dto.OrderResponseDto;
import com.store.payment.dto.OrderStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "order-service", url = "${order-service.host}:${order-service.port}")
public interface OrderServiceClient {

    @PutMapping("api/orders/{id}/status")
    OrderResponseDto updateOrderStatus(@PathVariable("id") UUID orderId,
                                       @RequestParam("newStatus") OrderStatus newStatus);
}
