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

    @GetMapping("/result/{resultId}")
    public List<Answer> getAnswersForResult(@PathVariable Integer resultId) {
        return answerService.getAnswersForResult(resultId);
    }

    @GetMapping("/result/{resultId}/question/{questionId}")
    public List<Answer> getAnswersForResultAndQuestion(@PathVariable Integer resultId, @PathVariable String questionId) {
        return answerService.getAnswersForResultAndQuestion(resultId, questionId);
    }

    @PostMapping
    public Answer saveAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @GetMapping("/solvedQuiz/{resultId}/calculateScore")
    public Double calculateScoreForSolvedQuiz(@PathVariable Integer resultId) {
        return answerService.calculateScoreForSolvedQuiz(resultId);
    }

    @GetMapping("/result/{resId}/question/{questionId}/calculateScore")
    public Double calculateScoreForQuestion(@PathVariable Integer resId, @PathVariable String questionId) {
        return answerService.calculateScoreForQuestion(resId, questionId);
    }

        @GetMapping("/result/{resId}/checkChosenAnswers")
    public Double checkChosenAnswers(@PathVariable Integer resId) {
        return answerService.checkChosenAnswersForResult(resId);
    }

    @PutMapping("/setCorrect")
    public Answer updateCorrect(@RequestBody SetAnsCorrectDTO ansCorrectDTO) {
        return answerService.setCorrect(ansCorrectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable String id) {
        answerService.deleteAnswer(id);
    }

    @DeleteMapping("/all")
    public void deleteAllAnswers() {
        answerService.deleteAllAnswers();
    }

}
