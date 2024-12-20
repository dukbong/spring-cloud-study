package study.cloud_user_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import study.cloud_user_service.config.FeignConfig;
import study.cloud_user_service.response.OrderInfo;
import study.cloud_user_service.template.GetOrderResponseWrapper;

import java.util.List;

@FeignClient(name = "cloud-order-service")
// 여기서 name 은 application.name을 의미한다.
public interface OrderServiceClient {

    @GetMapping("/{userId}/orders")
    GetOrderResponseWrapper getOrders(@PathVariable("userId") String userId);
}
