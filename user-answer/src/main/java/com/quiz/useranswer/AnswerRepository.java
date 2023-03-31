package com.quiz.useranswer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnswerRepository extends MongoRepository<Answer, String> {

    // List<Answer> findByQuestionIdAndSolverId(String questionId, Integer solverId);

    // List<Answer> findByQuestionIdAndSolverIdAndType(String questionId, Integer SolverId, AnswerType type);

    List<Answer> findByQuizResultId(Integer quizResultId);

    List<Answer> findByQuizResultIdAndQuestionId(Integer resultId, String questionId);

    // lista odgovora za rezultat po tipu
    List<Answer> findByQuizResultIdAndType(Integer quizResultId, AnswerType type);

}
