package com.quiz.quiz.controller;

import com.quiz.quiz.dto.QuestionReqDTO;
import com.quiz.quiz.model.Question;
import com.quiz.quiz.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionService.getAllQuestions(pageable);
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Integer id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getAllQuestionsForQuiz(@PathVariable Integer quizId) {
        return questionService.getAllQuestionsForQuiz(quizId);
    }

    @PostMapping
    public Question saveQuestion(@RequestBody QuestionReqDTO dto) {
        return questionService.saveQuestion(dto);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Integer id, @RequestBody QuestionReqDTO dto) {
        return questionService.updateQuestion(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
