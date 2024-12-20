package study.cloud_rabbitmq_consumer.repository;

import org.apache.ibatis.annotations.Mapper;
import study.cloud_rabbitmq_consumer.dao.Order;

@Mapper
public interface OrderRepository {

    public void createOrder(Order order);

}
