package com.quiz.takequiz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeQuizRepository extends JpaRepository<TakeQuiz, Integer> {

    // lista rjesenih kvizova za korisnika
    Page<TakeQuiz> findBySolverId(Integer solverId, Pageable pageable);

    // lista rezultata za kviz
    Page<TakeQuiz> findByQuizId(Integer quizId, Pageable pageable);

    TakeQuiz findByQuizIdAndSolverId(Integer quizId, Integer solverId);

    // da li postoji rezultata za kviz i korisnika
    boolean existsByQuizIdAndSolverId(Integer quizId, Integer solverId);

    boolean existsByQuizId(Integer quizId);   // da li postoje rezultati za kviz
}
