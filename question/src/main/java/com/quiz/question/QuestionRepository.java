package com.quiz.question;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {

    List<Question> findByQuizId(Integer quizId);

    Long deleteByQuizId(Integer quizId);
}
