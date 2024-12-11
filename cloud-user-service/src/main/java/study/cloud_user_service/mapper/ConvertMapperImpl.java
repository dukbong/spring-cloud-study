package study.cloud_user_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import study.cloud_user_service.dto.UserDto;
import study.cloud_user_service.entity.User;

/***
 * 책임을 분리하기 위해 생성
 */
@Component
@RequiredArgsConstructor
public class ConvertMapperImpl implements ConvertMapper{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User toUserEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .userId(userDto.getUserId())
                .encryptedPwd(bCryptPasswordEncoder.encode(userDto.getPwd()))
                .build();
    }

    @Override
    public UserDto toUserDto(User user) {
        return null;
    }

}
