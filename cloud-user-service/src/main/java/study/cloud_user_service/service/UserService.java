package study.cloud_user_service.service;

import study.cloud_user_service.dto.UserDto;
import study.cloud_user_service.response.UserInfo;

import java.util.List;

public interface UserService {

    Long createUser(UserDto userDto);

    List<UserInfo> getAllUsers();
    List<UserInfo> getAllUsers2();

    UserInfo getUserByUserId(Long userId);
}
