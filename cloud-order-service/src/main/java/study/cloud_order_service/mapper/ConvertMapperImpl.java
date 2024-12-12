package study.cloud_order_service.mapper;

import org.springframework.stereotype.Component;
import study.cloud_order_service.dto.OrderDto;
import study.cloud_order_service.entity.Order;

/***
 * 책임을 분리하기 위해 생성
 */
@Component
public class ConvertMapperImpl implements ConvertMapper{

    @Override
    public Order toOrderEntity(OrderDto orderDto) {
        return Order.builder()
                .productId(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .userId(orderDto.getUserId())
                .orderId(orderDto.getOrderId())
                .build();
    }

    @Override
    public OrderDto toOrderDto(Order user) {
        return null;
    }

}
