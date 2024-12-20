package study.cloud_order_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import study.cloud_order_service.dto.OrderDto;
import study.cloud_order_service.response.OrderInfo;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderDto orderDto);
    OrderInfo getOrderByOrderId(String orderId);
    List<OrderInfo> getOrdersByUserId(String userId);
}
