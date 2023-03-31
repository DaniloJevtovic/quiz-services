package com.quiz.useranswer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.quiz.useranswer", "com.quiz.rabbitmq"})
@EnableFeignClients(basePackages = "com.quiz.clients")
public class AnswerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnswerApplication.class, args);
    }
}
