package com.quiz.choice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelQuizConfig {

    @Value("${rabbitmq.exchanges.del-quiz}")
    private String exchange;

    @Value("${rabbitmq.queues.del-quiz-choices-queue}")
    private String queue;

    @Value("${rabbitmq.routing-keys.del-quiz-key}")
    private String routingKey;

    @Bean
    public TopicExchange topicDeleteQuizExchange() {
        return new TopicExchange(this.exchange);
    }

    @Bean
    public Queue deleteQuizQueue() {
        return new Queue(this.queue);
    }

    @Bean
    public Binding deleteQuizBinding() {
        return BindingBuilder
                .bind(deleteQuizQueue())
                .to(topicDeleteQuizExchange())
                .with(this.routingKey);
    }
}
