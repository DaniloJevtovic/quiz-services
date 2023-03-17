package com.quiz.choice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.quiz.choice", "com.quiz.rabbitmq"})
public class ChoiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChoiceApplication.class, args);
    }
}
