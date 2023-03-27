package com.quiz.useranswer;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/answers")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping
    public Page<Answer> getAllAnswers(@PageableDefault(size = 10) Pageable pageable) {
        return answerService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable String id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping("/question/{questionId}/solver/{solverId}")
    public List<Answer> getAnswersForQuestionAndSolver(@PathVariable String questionId, @PathVariable Integer solverId) {
        return answerService.getAnswersForQuestionAndSolver(questionId, solverId);
    }

    @GetMapping("/question/{questionId}/solver/{solverId}/type/{type}")
    public List<Answer> getAnswersForQuestionSolverAndType(
            @PathVariable String questionId, @PathVariable Integer solverId, @PathVariable AnswerType type) {
        return answerService.getAnswersForQuestionSolverAndType(questionId, solverId, type);
    }

    @GetMapping("/result/{resultId}")
    public List<Answer> getAnswersForResult(@PathVariable Integer resultId) {
        return answerService.getAnswersForResult(resultId);
    }

    @PostMapping
    public Answer saveAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @GetMapping("/solvedQuiz/{id}/calculateScore")
    public Double calculateScoreForSolvedQuiz(@PathVariable Integer resultId) {
        return answerService.calculateScoreForSolvedQuiz(resultId);
    }

    @GetMapping("/question/{questionId}/calculateScore/solver/{solverId}")
    public Double calculateScoreForQuestion(@PathVariable String questionId, Integer solverId) {
        return answerService.calculateScoreForQuestion(questionId, solverId);
    }

    @PutMapping("/setCorrect")
    public Answer updateCorrect(@RequestBody SetAnsCorrectDTO ansCorrectDTO) {
        return answerService.setCorrect(ansCorrectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable String id) {
        answerService.deleteAnswer(id);
    }

}
