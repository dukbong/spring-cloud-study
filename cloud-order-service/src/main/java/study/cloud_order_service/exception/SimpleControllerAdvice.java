package study.cloud_order_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.cloud_order_service.response.ExceptionInfo;
import study.cloud_order_service.response.OrderResponseType;

@RestControllerAdvice
public class SimpleControllerAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<OrderResponseType<ExceptionInfo>> IllegalStateExceptionHandler(OrderNotFoundException e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(e.getStatus(), e.getStatus().value(), e.getMessage());
        return ResponseEntity.badRequest().body(new OrderResponseType<>(exceptionInfo));
    }

}
