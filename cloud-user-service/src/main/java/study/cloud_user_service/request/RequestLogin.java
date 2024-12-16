package study.cloud_user_service.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {
    @NotNull(message = "Email cannot be null.")
    @Size(min = 2, message = "Email not be less than two characters.")
    @Email
    private String email;

    @NotNull(message = "password cannot be null.")
    @Size(min = 8, max = 16, message = "password must be equal or grater than 8 characters and less than 16 characters.")
    private String password;
}
