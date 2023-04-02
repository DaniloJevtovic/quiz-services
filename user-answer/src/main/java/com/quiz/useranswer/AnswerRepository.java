package com.quiz.useranswer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnswerRepository extends MongoRepository<Answer, String> {

    List<Answer> findByQuizResultId(Integer quizResultId);

    List<Answer> findByQuestionId(String questionId);
}
