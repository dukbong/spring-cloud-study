package study.cloud_order_service.mapper;

import study.cloud_order_service.dto.OrderDto;
import study.cloud_order_service.entity.Order;

public interface ConvertMapper {
    Order toOrderEntity(OrderDto userDto);
    OrderDto toOrderDto(Order user);
}
