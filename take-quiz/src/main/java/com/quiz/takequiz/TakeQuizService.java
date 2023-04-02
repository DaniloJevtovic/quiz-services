package com.quiz.takequiz;

import com.quiz.clients.answer.AnswerClient;
import com.quiz.clients.notification.NotificationDTO;
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
    private final AnswerClient answerClient;

    public Page<TakeQuiz> getAll(Pageable pageable) {
        return takeQuizRepository.findAll(pageable);
    }

    public TakeQuiz getSolvedQuizById(Integer id) {
        return takeQuizRepository.findById(id).orElse(null);
    }

    public TakeQuiz getResultForQuizAndSolver(Integer quizId, Integer solverId) {
        return takeQuizRepository.findByQuizIdAndSolverId(quizId, solverId);
    }

    public Page<TakeQuiz> getSolvedQuizesForUser(Integer userId, Pageable pageable) {
        return takeQuizRepository.findBySolverId(userId, pageable);
    }

    public Page<TakeQuiz> getResultsForQuiz(Integer quizId, Pageable pageable) {
        return takeQuizRepository.findByQuizId(quizId, pageable);
    }

    // start quiz
    public TakeQuiz takeQuiz(TakeQuizReqDTO dto) {
        TakeQuiz checkResult = getResultForQuizAndSolver(dto.quizId(), dto.solverId());

        if (checkResult != null) return checkResult;

        TakeQuiz takeQuiz = new TakeQuiz();
        takeQuiz.setQuizName(dto.quizName());
        takeQuiz.setQuizId(dto.quizId());
        takeQuiz.setQuizOwnerId(dto.ownerId());
        takeQuiz.setDurationOfSolving(0);
        takeQuiz.setResult(0.0);
        takeQuiz.setDateOfSolving(LocalDateTime.now());
        takeQuiz.setSolverId(dto.solverId());

        return takeQuizRepository.save(takeQuiz);
    }

    // kad se pritisne dugme finish ili istekne vrijeme uraditi update rezultata
    public TakeQuiz finishSolving(Integer takeQuizId, UpdateSolvedQuiz dto) {
        TakeQuiz solvedQuiz = getSolvedQuizById(takeQuizId);

        solvedQuiz.setResult(answerClient.calculateScoreForSolvedQuiz(takeQuizId)); // poziv answer servisa
        solvedQuiz.setDurationOfSolving(dto.durationOfSolving());
        solvedQuiz.setResultStatus(dto.status());   // na frontu provjeriti da li kviz ima samo ponudjene odgovore

        // poziv ms za povecavanje broja rjesavanja u kvizu
        rabbitMQMessageProducer.publish(solvedQuiz.getQuizId(), "solves.exchange", "solves.routing-key");
        // notifikacija vlasniku kviza da mu je kviz rjesavan
        rabbitMQMessageProducer.publish(
                new NotificationDTO(
                        "Vas kviz: " + solvedQuiz.getQuizName() + " je rjesavan!", solvedQuiz.getQuizOwnerId()),
                "notification.exchange",
                "notification.routing-key"
        );


        return takeQuizRepository.save(solvedQuiz);
    }

    // dodavanje ili oduzimanje poena (+ dodavanje, - oduzimanje)
    public void updatePoints(Integer id, Double points) {
        TakeQuiz solvedQuiz = getSolvedQuizById(id);
        solvedQuiz.setResult(solvedQuiz.getResult() + points);
        takeQuizRepository.save(solvedQuiz);
    }

    // kad vlasnik kviza pregleda sve odgovore - oznaciti kao ocjenjene
    public TakeQuiz updateResultStatus(Integer resId, ResultStatus status) {
        TakeQuiz result = getSolvedQuizById(resId);
        result.setResultStatus(status);
        return result;
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
