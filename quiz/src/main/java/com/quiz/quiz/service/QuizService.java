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

    public Quiz getById(Integer id) {
        return quizRepository.findById(id).orElse(null);
    }

    public List<Quiz> getQuizziesForUser(Integer userId) {
        return quizRepository.findByOwnerId(userId);
    }

    public Quiz saveQuiz(QuizReqDTO quizReqDTO) {
        Quiz quiz = new Quiz();
        quiz.setName(quizReqDTO.name());
        quiz.setDescription(quizReqDTO.description());
        quiz.setDuration(quizReqDTO.duration());
        quiz.setStatus(QuizStatus.INACTIVE);
        quiz.setCreated(LocalDateTime.now());
        quiz.setLastUpdate(LocalDateTime.now());
        quiz.setOwnerId(quizReqDTO.ownerId());

        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Integer quizId, QuizReqDTO quizReqDTO) {
        Quiz quiz = getById(quizId);
        quiz.setName(quizReqDTO.name());
        quiz.setDescription(quizReqDTO.description());
        quiz.setLastUpdate(LocalDateTime.now());
        quiz.setDuration(quizReqDTO.duration());

        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Integer id) {
        Quiz quiz = getById(id);
        quiz.setStatus(QuizStatus.DELETED);
        quizRepository.save(quiz);

        // provjeriti da li je kviz radjen, tj da li postoje rezulatati za njega
        // ako ne skroz ga obrisati, zajedno sa njegovim pitanjima
    }
}
