package com.quiz.question.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelQuestionsConfig {

    @Value("${rabbitmq.exchanges.del-quiz}")
    private String exchange;

    @Value("${rabbitmq.queues.del-quiz-questions-queue}")
    private String queue;

    @Value("${rabbitmq.routing-keys.del-quiz-key}")
    private String routingKey;

    @Bean
    public TopicExchange topicDeleteExchange() {
        return new TopicExchange(this.exchange);
    }

    @Bean
    public Queue deleteQuestionsQueue() {
        return new Queue(this.queue);
    }

    @Bean
    public Binding deleteQuestionBinding() {
        return BindingBuilder
                .bind(deleteQuestionsQueue())
                .to(topicDeleteExchange())
                .with(this.routingKey);
    }
}
