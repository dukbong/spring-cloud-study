package study.cloud_order_service.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import study.cloud_order_service.dto.OrderDto;

import java.util.UUID;

@Data
public class RequestOrder {
    @NotNull(message = "ProductId must not be null")
    @Size(max = 120, message = "ProductId must be 120 characters or fewer")
    private String productId;
    @NotNull(message = "Quantity must not be null")
    private Integer qty;
    @NotNull(message = "UnitPrice must not be null")
    private Integer unitPrice;

    public OrderDto toOrderDto(String userId) {
        Integer totalPrice = this.qty * this.unitPrice;
        String orderId = UUID.randomUUID().toString();
        return new OrderDto(this.productId, this.qty, this.unitPrice, totalPrice, orderId, userId);
    }
}
