package com.quiz.quiz.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolvesConfig {

    @Value("${rabbitmq.exchanges.solves}")
    private String exchange;

    @Value("${rabbitmq.queues.solves-queue}")
    private String queue;

    @Value("${rabbitmq.routing-keys.solves-key}")
    private String routingKey;

    @Bean
    public TopicExchange increaseSolvesExchange() {
        return new TopicExchange(this.exchange);
    }

    @Bean
    public Queue increaseSolvesQueue() {
        return new Queue(this.queue);
    }

    @Bean
    public Binding increaseSolvesBinding() {
        return BindingBuilder
                .bind(increaseSolvesQueue())
                .to(increaseSolvesExchange())
                .with(this.routingKey);
    }
}
