package com.quiz.notification.rabbitmq;

import com.quiz.notification.NotificationDTO;
import com.quiz.notification.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification-queue}")
    public void sendNotification(NotificationDTO notificationDTO) {
        notificationService.save(notificationDTO);
    }
}
