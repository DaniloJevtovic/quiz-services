package com.quiz.quiz.rabbitmq;

import com.quiz.quiz.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final QuizService quizService;

    @RabbitListener(queues = "${rabbitmq.queues.solves-queue}")
    public void increaseNumOfSolves(Integer quizId) {
        quizService.increaseNumOfSolves(quizId);
    }
}
