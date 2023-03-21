package com.quiz.notification.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendNotificationConfig {

    @Value("${rabbitmq.exchanges.notification}")
    private String exchange;

    @Value("${rabbitmq.queues.notification-queue}")
    private String queue;

    @Value("${rabbitmq.routing-keys.notification-key}")
    private String routingKey;

    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(this.exchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(this.queue);
    }

    @Bean
    public Binding notificationsBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(notificationExchange())
                .with(this.routingKey);
    }
}
