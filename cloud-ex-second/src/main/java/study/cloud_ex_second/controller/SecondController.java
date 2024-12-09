package study.cloud_ex_second.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class SecondController {

    @GetMapping("/welcome")
    public String home() {
        return "This is Second Project.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        return header;
    }

    @GetMapping("/check")
    public String check() {
        return "Hi! This is a message from Second Service";
    }

}
