package study.cloud_order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.cloud_order_service.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(String orderId);
    List<Order> findByUserId(String userId);
}
