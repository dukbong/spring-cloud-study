package study.cloud_user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.cloud_user_service.dto.UserDto;
import study.cloud_user_service.entity.User;
import study.cloud_user_service.mapper.ConvertMapper;
import study.cloud_user_service.repository.UserRepository;
import study.cloud_user_service.response.OrderInfo;
import study.cloud_user_service.response.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ConvertMapper convertMapperImpl;

    @Override
    public Long createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        User user = convertMapperImpl.toUserEntity(userDto);
        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }

    @Override
    public List<UserInfo> getAllUsers() {
        List<User> findUsers = userRepository.findAll();
        return findUsers.stream().map(u -> new UserInfo(u.getEmail(), u.getName(), u.getUserId(), u.getCreatedDate())).toList();
    }
    @Override
    public List<UserInfo> getAllUsers2() {
        // DTO Projection 사용으로 DTO 변환 과정 생략
        return userRepository.findAllUserInfo();
    }

    @Override
    public UserInfo getUserByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Could not find user for getUserByUserId( %s ).", userId));
        }
        User user = optionalUser.get();
        // 임시
        List<OrderInfo> orderInfoList = new ArrayList<>();
        return new UserInfo(user.getEmail(), user.getName(), user.getUserId(), user.getCreatedDate(), orderInfoList);
    }

}
