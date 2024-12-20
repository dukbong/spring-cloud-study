package study.cloud_order_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import study.cloud_order_service.dto.OrderDto;
import study.cloud_order_service.entity.Order;
import study.cloud_order_service.entity.WorkStatus;
import study.cloud_order_service.exception.OrderNotFoundException;
import study.cloud_order_service.mapper.ConvertMapper;
import study.cloud_order_service.repository.OrderRepository;
import study.cloud_order_service.response.OrderInfo;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ConvertMapper convertMapper;
    private final AmqpTemplate amqpTemplate;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Long createOrder(OrderDto orderDto) {
        Order order = convertMapper.toOrderEntity(orderDto);
//        Order savedOrder = orderRepository.save(order);
//        return savedOrder.getId();
//        amqpTemplate.convertAndSend("testQueue", order.setWorkStatus(WorkStatus.SAVE));
        String orderMessage = convertOrderToMessage(order.setWorkStatus(WorkStatus.SAVE));
        Message requestMessage = createRequestMessage(orderMessage);
        Message responseMessage = sendAndReceiveMessage(requestMessage);

        return extractResult(responseMessage);
    }

    private String convertOrderToMessage(Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("주문 변환 오류", e);
        }
    }

    private Message createRequestMessage(String orderMessage) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setReplyTo("replyQueue");
        messageProperties.setCorrelationId(UUID.randomUUID().toString());
        return new Message(orderMessage.getBytes(), messageProperties);
    }

    private Message sendAndReceiveMessage(Message requestMessage) {
        Message responseMessage = amqpTemplate.sendAndReceive("testQueue", requestMessage);
        if (responseMessage == null) {
            throw new IllegalStateException("응답 메시지가 없음.");
        }
        return responseMessage;
    }

    private Long extractResult(Message responseMessage) {
        return (long) ByteBuffer.wrap(responseMessage.getBody()).getInt();
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
