package com.quiz.selectanswer;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/selected-answers")
@AllArgsConstructor
public class SelectAnswerController {

    private final SelectAnswerService selectAnswerService;

    @GetMapping
    public Page<SelectAnswer> getAllAnswers(@PageableDefault(size = 10) Pageable pageable) {
        return selectAnswerService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public SelectAnswer getAnswerById(@PathVariable String id) {
        return selectAnswerService.getAnswerById(id);
    }

    @GetMapping("/result/{resId}/choice/{choiceId}")
    public SelectAnswer getAnswerByResultAndChoice(@PathVariable Integer resId, @PathVariable String choiceId) {
        return selectAnswerService.getAnswerForResultAndChoice(resId, choiceId);
    }

    @GetMapping("/result/{resultId}")
    public List<SelectAnswer> getSelectedAnswersForResult(@PathVariable Integer resultId) {
        return selectAnswerService.getSelectedAnswersForResult(resultId);
    }

    @GetMapping("/question/{questionId}")
    public List<SelectAnswer> getAnswersForQuestion(@PathVariable String questionId) {
        return selectAnswerService.getSelectedAnswersForQuestion(questionId);
    }

    @PostMapping
    public SelectAnswer selectAnswer(@RequestBody SelectAnswer answer) {
        return selectAnswerService.selectAnswer(answer);
    }

    @GetMapping("/solvedQuiz/{resultId}/calculateScore")
    public Double calculateScoreForSolvedQuiz(@PathVariable Integer resultId) {
        return selectAnswerService.calculateScoreForSolvedQuiz(resultId);
    }

    @GetMapping("/question/{questionId}/calculateScore")
    public Double calculateScoreForQuestion(@PathVariable String questionId) {
        return selectAnswerService.calculateScoreForQuestion(questionId);
    }

    @GetMapping("/result/{resId}/checkChosenAnswers")
    public Double checkChosenAnswers(@PathVariable Integer resId) {
        return selectAnswerService.checkChosenAnswersForResult(resId);
    }

    @PutMapping("/setCorrect")
    public SelectAnswer updateCorrect(@RequestBody SetAnsCorrectDTO ansCorrectDTO) {
        return selectAnswerService.setCorrect(ansCorrectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable String id) {
        selectAnswerService.deleteSelectedAnswer(id);
    }

    @DeleteMapping("/all")
    public void deleteAllAnswers() {
        selectAnswerService.deleteAll();
    }

}
