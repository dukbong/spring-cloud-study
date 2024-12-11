package study.cloud_user_service.mapper;

import study.cloud_user_service.dto.UserDto;
import study.cloud_user_service.entity.User;

public interface ConvertMapper {
    User toUserEntity(UserDto userDto);
    UserDto toUserDto(User user);
}
