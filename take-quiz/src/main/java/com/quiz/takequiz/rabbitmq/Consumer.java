package com.quiz.takequiz.rabbitmq;

import com.quiz.clients.result.UpdatePointsDTO;
import com.quiz.takequiz.TakeQuizService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final TakeQuizService takeQuizService;

    @RabbitListener(queues = "${rabbitmq.queues.results-queue}")
    public void updateResult(UpdatePointsDTO dto) {
        takeQuizService.updatePoints(dto.resultId(), dto.points());
    }
}
