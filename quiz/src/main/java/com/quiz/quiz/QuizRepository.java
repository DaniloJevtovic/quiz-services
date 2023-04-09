package com.quiz.quiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    List<Quiz> findByOwnerId(Integer userId);

    Page<Quiz> findByCategoryIdAndStatus(Integer categoryId, QuizStatus status, Pageable pageable);

    List<Quiz> findByCategoryIdAndNameContains(Integer catId, String name);

    List<Quiz> findByCategoryIdAndStatusAndNameContains(Integer catId, QuizStatus status, String name);

    Double deleteByCategoryId(Integer categoryId);

    Boolean existsByCategoryId(Integer categoryId);
}
