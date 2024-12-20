package study.cloud_user_service.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import study.cloud_user_service.dto.UserDto;

@Data
public class RequestUser {
    @NotNull(message = "Email must not be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotNull(message = "Name must not be null")
    @Size(min = 2, message = "Name not be less than two characters")
    private String name;

    @NotNull(message = "Password must not be null")
    @Size(min = 8, message = "Password must be equal or grater than 8 characters")
    private String pwd;


    public UserDto toUserDto() {
        return new UserDto(this.email, this.name, this.pwd);
    }
}
