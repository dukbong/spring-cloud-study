package study.cloud_rabbitmq_consumer.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
public class Order {

    private Long id;
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String userId;
    private String orderId;
    private String workStatus;
    private String createdDate;
    private String lastModifiedDate;

}
