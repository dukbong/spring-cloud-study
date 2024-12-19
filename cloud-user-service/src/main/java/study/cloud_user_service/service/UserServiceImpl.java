package study.cloud_user_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import study.cloud_user_service.client.OrderServiceClient;
import study.cloud_user_service.config.detail.CustomUserDetail;
import study.cloud_user_service.dto.UserDto;
import study.cloud_user_service.entity.User;
import study.cloud_user_service.exception.UserNotFoundException;
import study.cloud_user_service.mapper.ConvertMapper;
import study.cloud_user_service.repository.UserRepository;
import study.cloud_user_service.response.UserInfo;
import study.cloud_user_service.template.GetOrderResponseWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ConvertMapper convertMapperImpl;
    private final RestTemplate restTemplate;
    private final Environment env;

    private final OrderServiceClient orderServiceClient;

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
        if(findUsers.isEmpty()) {
            throw new UserNotFoundException("Could not find any users.", HttpStatus.BAD_REQUEST);
        }
        return findUsers.stream().map(u -> new UserInfo(u.getEmail(), u.getName(), u.getUserId(), u.getCreatedDate())).toList();
    }
    @Override
    public List<UserInfo> getAllUsers2() {
        // DTO Projection 사용으로 DTO 변환 과정 생략
        return userRepository.findAllUserInfo();
    }

    @Override
    public UserInfo getUserByUserId(String userId) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("Could not find user for getUserByUserId( %s ).", userId), HttpStatus.BAD_REQUEST);
        }
        User user = optionalUser.get();
        
//        RestTemplate 사용
//        String orderUrl = String.format(Objects.requireNonNull(env.getProperty("order_service.url")), userId);
//        ResponseEntity<GetOrderResponseWrapper> orderResponseResult = restTemplate.exchange(orderUrl,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {});
        
//        Feign 사용
//        GetOrderResponseWrapper orderResponseResult = null;
//        try {
//            orderResponseResult = orderServiceClient.getOrders(userId);
//        } catch (FeignException e) {
//            log.error(e.getMessage());
//        }
//        List<OrderInfo> orderInfoList = null;
//        if(orderResponseResult != null) {
//            orderInfoList = orderResponseResult.getData();
//        }

//        Feign ErrorDecoder 사용
        GetOrderResponseWrapper orderResponseResult = orderServiceClient.getOrders(userId);

        return new UserInfo(user.getEmail(), user.getName(), user.getUserId(), user.getCreatedDate(), orderResponseResult.getData());
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(findUser.isEmpty()) {
            throw new UserNotFoundException("User not found Email = " + userEmail, HttpStatus.BAD_REQUEST);
        }
        User user = findUser.get();
        return new CustomUserDetail(user.getEmail(), user.getEncryptedPwd(), user.getUserId(), /* 권한 */new ArrayList<>());
    }
}
