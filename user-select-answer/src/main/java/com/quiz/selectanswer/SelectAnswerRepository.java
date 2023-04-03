package com.quiz.selectanswer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SelectAnswerRepository extends MongoRepository<SelectAnswer, String> {

    List<SelectAnswer> findByQuizResultId(Integer quizResultId);

    List<SelectAnswer> findByQuestionId(String questionId);

    SelectAnswer findByQuizResultIdAndChoiceId(Integer quizId, String choiceId);

}
