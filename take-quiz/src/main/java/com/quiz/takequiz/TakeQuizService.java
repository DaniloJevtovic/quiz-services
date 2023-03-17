package com.quiz.takequiz;

import com.quiz.rabbitmq.RabbitMQMessageProducer;
import com.quiz.takequiz.dto.TakeQuizReqDTO;
import com.quiz.takequiz.dto.UpdateSolvedQuiz;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TakeQuizService {

    private final TakeQuizRepository takeQuizRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Page<TakeQuiz> getAll(Pageable pageable) {
        return takeQuizRepository.findAll(pageable);
    }

    public TakeQuiz getSolvedQuizById(Integer id) {
        return takeQuizRepository.findById(id).orElse(null);
    }

    public TakeQuiz getByQuizAndSolver(Integer quizId, Integer solverId) {
        return takeQuizRepository.findByQuizIdAndSolverId(quizId, solverId);
    }

    public Page<TakeQuiz> getSolvedQuiziesForUser(Integer userId, Pageable pageable) {
        return takeQuizRepository.findBySolverId(userId, pageable);
    }

    public Page<TakeQuiz> getSolvedQuiziesForQuiz(Integer quizId, Pageable pageable) {
        return takeQuizRepository.findByQuizId(quizId, pageable);
    }

    public TakeQuiz takeQuiz(TakeQuizReqDTO dto) {
        // provjera da li je korisnik vec rjesavao kviz
        TakeQuiz checkResult = getByQuizAndSolver(dto.quizId(), dto.solverId());

        if (checkResult != null) return checkResult;

        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuizName(dto.quizName());
        takeQuiz.setQuizId(dto.quizId());
        takeQuiz.setDurationOfSolving(0);
        takeQuiz.setResult(0.0);
        takeQuiz.setDateOfSolving(LocalDateTime.now());
        takeQuiz.setSolverId(dto.solverId());

        // poziv ms za povecavanje broja rjesavanja u kvizu
        rabbitMQMessageProducer.publish(dto.quizId(), "solves.exchange", "solves.routing-key");

        return takeQuizRepository.save(takeQuiz);
    }

    // kad se pritisne dugme finish ili istekne vrijeme uraditi update rezultata
    // ili svaki put kad se sacuva odgovor update rezultata?
    public TakeQuiz updateSolvedQuiz(Integer takeQuizId, UpdateSolvedQuiz dto) {
        TakeQuiz solvedQuiz = getSolvedQuizById(takeQuizId);
        solvedQuiz.setResult(dto.result());
        solvedQuiz.setDurationOfSolving(dto.durationOfSolving());

        return takeQuizRepository.save(solvedQuiz);
    }

    public void updatePoints(Integer id, Double points) {
        TakeQuiz solvedQuiz = getSolvedQuizById(id);
        solvedQuiz.setResult(points);
        takeQuizRepository.save(solvedQuiz);
    }

    private boolean checkSolvesForQuizAndUser(Integer quizId, Integer solverId) {
        return takeQuizRepository.existsByQuizIdAndSolverId(quizId, solverId);
    }

    public boolean checkQuizSolved(Integer quizId) {
        return takeQuizRepository.existsByQuizId(quizId);
    }

    public void deleteSolvedQuiz(Integer id) {
        takeQuizRepository.deleteById(id);
    }

}
