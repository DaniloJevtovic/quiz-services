package com.quiz.choice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChoiceRepository extends MongoRepository<Choice, String> {

    // svi izbori (odgovori) za pitanje
    List<Choice> findByQuestionId(String questionId);

    // tacni izbori (odgovori) za pitanje
    List<Choice> findByQuestionIdAndCorrectTrue(String questionid);

    // ponudjeni odgovori za kviz
    List<Choice> findByQuizId(Integer quizId);

    Long deleteByQuestionId(String questionId);

    Long deleteByQuizId(Integer quizId);
}
