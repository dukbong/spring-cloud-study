package study.cloud_user_service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import study.cloud_user_service.dto.UserDto;
import study.cloud_user_service.response.UserInfo;

import java.util.List;

public interface UserService extends UserDetailsService {

    Long createUser(UserDto userDto);

    List<UserInfo> getAllUsers();
    List<UserInfo> getAllUsers2();

    UserInfo getUserByUserId(String userId);

}
