package study.cloud_user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private LocalDateTime createAt;

    private String encryptedPwd;

    // RequestUser [controller] -> UserDto [service]
    public UserDto(String email, String name, String pwd) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
    }

}
