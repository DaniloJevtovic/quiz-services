package com.quiz.quiz.repository;

import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.model.QuizStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    List<Quiz> findByOwnerId(Integer userId);

    Page<Quiz> findByCategoryIdAndStatus(Integer categoryId, QuizStatus status, Pageable pageable);
}
