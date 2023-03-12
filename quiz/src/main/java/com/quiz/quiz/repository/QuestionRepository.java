package com.quiz.quiz.repository;

import com.quiz.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByQuizId(Integer quizId);
}
