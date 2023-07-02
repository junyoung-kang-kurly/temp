package com.example.action;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostConstruct
    public void delay() throws InterruptedException {
        System.out.println("20초 지연후 실행하기");
        Thread.sleep(20_000);
        System.out.println("실행합니다");
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
