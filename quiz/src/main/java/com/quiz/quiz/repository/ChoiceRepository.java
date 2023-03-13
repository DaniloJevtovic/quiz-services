package com.quiz.quiz.repository;

import com.quiz.quiz.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

    // svi izbori (odgovori) za pitanje
    List<Choice> findByQuestionId(Integer questionId);

    // tacni izbori (odgovori) za pitanje
    List<Choice> findByQuestionIdAndCorrectTrue(Integer questionid);
}
