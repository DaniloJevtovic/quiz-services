package com.quiz.takequiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quiz.takequiz", "com.quiz.rabbitmq"})
public class TakeQuizApplication {
    public static void main(String[] args) {
        SpringApplication.run(TakeQuizApplication.class, args);
    }
}
