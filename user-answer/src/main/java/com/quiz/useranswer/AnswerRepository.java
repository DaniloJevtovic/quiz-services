package com.quiz.useranswer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnswerRepository extends MongoRepository<Answer, String> {

    List<Answer> findByQuestionIdAndSolverId(Integer questionId, Integer solverId);

    List<Answer> findByQuizResultId(Integer quizResultId);
}
