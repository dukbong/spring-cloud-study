package study.cloud_ex_second.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/second-service")
@RequiredArgsConstructor
public class SecondController {

    private final Environment env;

    @GetMapping("/welcome")
    public String home() {
        return "This is Second Project.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        return header;
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("httpServletRequest port number >>> {}", request.getServerPort());
        return String.format("Hi! This is a message from Second Service >>> PORT %s", env.getProperty("local.server.port"));
    }

}
