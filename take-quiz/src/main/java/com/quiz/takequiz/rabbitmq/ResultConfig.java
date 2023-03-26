package com.quiz.takequiz.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultConfig {

    @Value("${rabbitmq.exchanges.results}")
    private String exchange;

    @Value("${rabbitmq.queues.results-queue}")
    private String queue;

    @Value("${rabbitmq.routing-keys.results-key}")
    private String routingKey;

    @Bean
    public TopicExchange resultsExchange() {
        return new TopicExchange(this.exchange);
    }

    @Bean
    public Queue resultsQueue() {
        return new Queue(this.queue);
    }

    @Bean
    public Binding updatePointsBinding() {
        return BindingBuilder
                .bind(resultsQueue())
                .to(resultsExchange())
                .with(this.routingKey);
    }
}
