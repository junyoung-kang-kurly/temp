package com.example.action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActionApplication {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("10초 지연후 실행하기");
        Thread.sleep(30_000);
        System.out.println("실행합니다");
        SpringApplication.run(ActionApplication.class, args);
    }

}
