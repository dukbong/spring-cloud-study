package study.cloud_order_service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionInfo {
    private HttpStatus status;
    private int statusCode;
    private String message;
}
