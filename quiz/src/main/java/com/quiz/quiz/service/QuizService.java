package com.quiz.quiz.service;

import com.quiz.quiz.dto.QuizReqDTO;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.model.QuizStatus;
import com.quiz.quiz.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public Page<Quiz> getAll(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    public Quiz getQuizById(Integer id) {
        return quizRepository.findById(id).orElse(null);
    }

    public List<Quiz> getQuizziesForUser(Integer userId) {
        return quizRepository.findByOwnerId(userId);
    }

    public Page<Quiz> getQuizziesForCategoryAndStatus(Integer categoryId, QuizStatus status, Pageable pageable) {
        return quizRepository.findByCategoryIdAndStatus(categoryId, status, pageable);
    }

    public Quiz createQuiz(QuizReqDTO quizReqDTO) {
        Quiz quiz = new Quiz();
        quiz.setName(quizReqDTO.name());
        quiz.setInstructions(quizReqDTO.instructions());
        quiz.setDuration(quizReqDTO.duration());
        quiz.setStatus(QuizStatus.INACTIVE);
        quiz.setCreated(LocalDateTime.now());
        quiz.setLastUpdate(LocalDateTime.now());
        quiz.setOwnerId(quizReqDTO.ownerId());
        quiz.setCategoryId(quizReqDTO.categoryId());

        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Integer quizId, QuizReqDTO quizReqDTO) {
        Quiz quiz = getQuizById(quizId);
        quiz.setName(quizReqDTO.name());
        quiz.setInstructions(quizReqDTO.instructions());
        quiz.setLastUpdate(LocalDateTime.now());
        quiz.setDuration(quizReqDTO.duration());
        quiz.setCategoryId(quizReqDTO.categoryId());

        return quizRepository.save(quiz);
    }

    public Quiz changeQuizStatus(Integer quizId, QuizStatus quizStatus) {
        Quiz quiz = getQuizById(quizId);
        quiz.setStatus(quizStatus);
        quizRepository.save(quiz);
        return quiz;
    }


    public void deleteQuiz(Integer id) {
        quizRepository.deleteById(id);
        // provjeriti da li je kviz radjen, tj da li postoje rezulatati za njega
        // ako ne skroz ga obrisati, zajedno sa njegovim pitanjima
    }
}
