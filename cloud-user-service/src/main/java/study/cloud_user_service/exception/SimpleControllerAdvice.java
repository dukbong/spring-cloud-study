package study.cloud_user_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.cloud_user_service.response.ExceptionInfo;
import study.cloud_user_service.response.UserResponseType;

@RestControllerAdvice
public class SimpleControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserResponseType<ExceptionInfo>> IllegalStateExceptionHandler(UserNotFoundException e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(e.getStatus(), e.getStatus().value(), e.getMessage());
        return ResponseEntity.badRequest().body(new UserResponseType<>(exceptionInfo));
    }

}
