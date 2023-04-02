package com.quiz.takequiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.quiz.takequiz", "com.quiz.rabbitmq"})
@EnableFeignClients(basePackages = "com.quiz.clients")
public class TakeQuizApplication {
    public static void main(String[] args) {
        SpringApplication.run(TakeQuizApplication.class, args);
    }
}
