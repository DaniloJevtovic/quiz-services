package com.quiz.choice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelQuestionConfig {

    @Value("${rabbitmq.exchanges.del-question}")
    private String exchange;

    @Value("${rabbitmq.queues.del-question-choices-queue}")
    private String queue;

    @Value("${rabbitmq.routing-keys.del-question-key}")
    private String routingKey;

    @Bean
    public TopicExchange topicDeleteQuestionExchange() {
        return new TopicExchange(this.exchange);
    }

    @Bean
    public Queue deleteQuestionQueue() {
        return new Queue(this.queue);
    }

    @Bean
    public Binding deleteQuestionBinding() {
        return BindingBuilder
                .bind(deleteQuestionQueue())
                .to(topicDeleteQuestionExchange())
                .with(this.routingKey);
    }
}
