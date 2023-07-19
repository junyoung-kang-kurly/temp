package com.example.action;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/ping")
    public ResponseEntity<String> ping(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(httpServletRequest.getRemoteAddr());
    }
    @GetMapping("/ping2")
    public ResponseEntity<String> ping2(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(HttpUtils.getRemoteIp(httpServletRequest));
    }

}
