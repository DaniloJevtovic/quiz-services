package com.quiz.quizcategory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.quiz.quizcategory", "com.quiz.rabbitmq"})
@EnableFeignClients(basePackages = "com.quiz.clients")
public class QuizCategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuizCategoryApplication.class, args);
    }
}
