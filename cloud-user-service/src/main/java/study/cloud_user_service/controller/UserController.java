package study.cloud_user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.cloud_user_service.request.RequestUser;
import study.cloud_user_service.response.UserInfo;
import study.cloud_user_service.response.UserResponseType;
import study.cloud_user_service.service.UserService;
import study.cloud_user_service.vo.Greeting;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userServiceImpl;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service. PORT : %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @GetMapping("/welcome2")
    public String welcome2() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponseType<Long>> createUser(@RequestBody RequestUser request) {
        Long saveUserId = userServiceImpl.createUser(request.toUserDto());
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseType<>(saveUserId));
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponseType<List<UserInfo>>> getAllUsers() {
        List<UserInfo> findUsers = userServiceImpl.getAllUsers();
        return ResponseEntity.ok().body(new UserResponseType<>(findUsers));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseType<UserInfo>> getUser(@PathVariable("userId") String userId) {
        UserInfo result = userServiceImpl.getUserByUserId(userId);
        return ResponseEntity.ok().body(new UserResponseType<>(result));
    }

}
