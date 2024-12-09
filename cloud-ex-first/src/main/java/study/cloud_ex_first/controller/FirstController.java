package study.cloud_ex_first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first-service")
public class FirstController {

    @GetMapping("/welcome")
    public String home() {
        return "This is First Project.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        return header;
    }

    @GetMapping("/check")
    public String check() {
        return "Hi! This is a message from First Service";
    }
}
