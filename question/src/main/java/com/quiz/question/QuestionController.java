package com.quiz.question;

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
    public Question getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getAllQuestionsForQuiz(@PathVariable Integer quizId) {
        return questionService.getAllQuestionsForQuiz(quizId);
    }

    @PostMapping
    public Question saveQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @PutMapping
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
    }

    @DeleteMapping("/quiz/{quizId}")
    public Long deleteAllQuestionsForQuiz(@PathVariable Integer quizId) {
        return questionService.deleteAllQuestionsForQuiz(quizId);
    }
}
