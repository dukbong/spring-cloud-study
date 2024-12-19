package study.cloud_user_service.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.cloud_user_service.response.OrderInfo;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponseWrapper {
    private List<OrderInfo> data;
}
