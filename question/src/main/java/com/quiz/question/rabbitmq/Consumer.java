package com.quiz.question.rabbitmq;

import com.quiz.question.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final QuestionService questionService;

    @RabbitListener(queues = "${rabbitmq.queues.del-quiz-questions-queue}")
    public void deleteQuestionsForQuiz(Integer quizId) {
        questionService.deleteAllQuestionsForQuiz(quizId);
    }
}
