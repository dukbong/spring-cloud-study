package study.cloud_user_service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    private String email;
    private String name;
    private String userId;
    private LocalDateTime createTime;

    private List<OrderInfo> orders;

    // 초기 getAllUsers
    public UserInfo(String email, String name, String userId, LocalDateTime createTime) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.createTime = createTime;
    }

    public UserInfo(String email, String name, String userId, LocalDateTime createTime, List<OrderInfo> orders) {
        this(email, name, userId, createTime);
        this.orders = orders;
    }

}
