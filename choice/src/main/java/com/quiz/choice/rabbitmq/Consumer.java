package com.quiz.choice.rabbitmq;

import com.quiz.choice.ChoiceService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final ChoiceService choiceService;

    @RabbitListener(queues = "${rabbitmq.queues.del-quiz-choices-queue}")
    public void deleteChoicesForQuiz(Integer quizId) {
        choiceService.deleteAlChoicesForQuz(quizId);
    }

    @RabbitListener(queues = "${rabbitmq.queues.del-question-choices-queue}")
    public void deleteChoicesForQuestion(String questionId) {
        choiceService.deleteAllCoicesForQuestion(questionId);
    }
}
