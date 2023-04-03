package com.quiz.selectanswer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.quiz.selectanswer", "com.quiz.rabbitmq"})
@EnableFeignClients(basePackages = "com.quiz.clients")
public class SelectAnswerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelectAnswerApplication.class, args);
    }
}
