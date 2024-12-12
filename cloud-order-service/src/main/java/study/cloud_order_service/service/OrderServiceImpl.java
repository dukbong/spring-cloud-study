package study.cloud_order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import study.cloud_order_service.dto.OrderDto;
import study.cloud_order_service.entity.Order;
import study.cloud_order_service.exception.OrderNotFoundException;
import study.cloud_order_service.mapper.ConvertMapper;
import study.cloud_order_service.repository.OrderRepository;
import study.cloud_order_service.response.OrderInfo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ConvertMapper convertMapper;

    @Override
    public Long createOrder(OrderDto orderDto) {
        Order order = convertMapper.toOrderEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    @Override
    public OrderInfo getOrderByOrderId(String orderId) {
        Optional<Order> findOrder = orderRepository.findByOrderId(orderId);
        if(findOrder.isPresent()) {
            Order order = findOrder.get();
            return new OrderInfo(order.getProductId(), order.getQty(), order.getUnitPrice(), order.getTotalPrice(), order.getCreatedDate(), order.getOrderId());
        }
        throw new OrderNotFoundException(String.format("Could not find order for getOrderByOrderId( %s ).", orderId), HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<OrderInfo> getOrdersByUserId(String userId) {
        List<Order> findOrder = orderRepository.findByUserId(userId);
        if(findOrder.isEmpty()) {
            throw new OrderNotFoundException(String.format("Could not find order for getOrdersByUserId( %s ).", userId), HttpStatus.BAD_REQUEST);
        }
        return findOrder.stream().map(o -> new OrderInfo(o.getProductId(), o.getQty(), o.getUnitPrice(), o.getTotalPrice(), o.getCreatedDate(), o.getOrderId())).toList();

    }
}
