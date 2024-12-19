package study.cloud_order_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.cloud_order_service.request.RequestOrder;
import study.cloud_order_service.response.OrderInfo;
import study.cloud_order_service.response.OrderResponseType;
import study.cloud_order_service.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final Environment env;
    private final OrderService orderServiceImpl;

    @GetMapping("/health_check")
    public String healthCheck() {
        return String.format("This is Order Service. PORT NUMBER = %s",env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<OrderResponseType<Long>> createOrder(@PathVariable("userId") String userId, @RequestBody RequestOrder request) {
        Long result = orderServiceImpl.createOrder(request.toOrderDto(userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponseType<>(result));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<OrderResponseType<List<OrderInfo>>> getOrders(@PathVariable("userId") String userId) {
        List<OrderInfo> result = orderServiceImpl.getOrdersByUserId(userId);
        return ResponseEntity.ok().body(new OrderResponseType<>(result));
    }



}
