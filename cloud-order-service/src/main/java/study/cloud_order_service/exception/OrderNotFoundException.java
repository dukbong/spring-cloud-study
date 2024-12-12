package study.cloud_order_service.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends RuntimeException {
    private HttpStatus status;

    public OrderNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
