package com.quiz.quiz.repository;

import com.quiz.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    List<Quiz> findByOwnerId(Integer userId);
}
