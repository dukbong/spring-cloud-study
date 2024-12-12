package study.cloud_user_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {
    private HttpStatus status;

    public UserNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
