package study.cloud_order_service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@SequenceGenerator(name = "orders_sequence_generator", sequenceName = "orders_sequence")
@Table(name = "orders")
public class Order extends Base{

//    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence_generator")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, unique = true)
    private String orderId;

    @Transient
    private WorkStatus workStatus;

    @Builder
    private Order(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String userId, String orderId) {
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.orderId = orderId;
    }

    public Order setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
        return this;
    }
}
