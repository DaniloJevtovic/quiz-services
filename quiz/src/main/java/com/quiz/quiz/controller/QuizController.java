package com.quiz.quiz.controller;

import com.quiz.quiz.dto.QuizReqDTO;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzies")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public Page<Quiz> getAllQuizzies(Pageable pageable) {
        return quizService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Integer id) {
        return quizService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Quiz> getAllQuizziesForUser(@PathVariable Integer userId) {
        return quizService.getQuizziesForUser(userId);
    }

    @PostMapping
    public Quiz saveQuiz(@RequestBody QuizReqDTO dto) {
        return quizService.saveQuiz(dto);
    }

    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Integer id, @RequestBody QuizReqDTO dto) {
        return quizService.updateQuiz(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Integer id) {
        quizService.deleteQuiz(id);
    }
}
