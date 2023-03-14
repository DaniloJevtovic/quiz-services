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
    public List<Answer> getAnswersForQuestionAndSolver(@PathVariable Integer questionId, @PathVariable Integer solverId) {
        return answerService.getAnswersForQuestionAndSolver(questionId, solverId);
    }

    @GetMapping("/result/{resultId}")
    public List<Answer> getAnswersForResult(@PathVariable Integer resultId) {
        return answerService.getAnswersForResult(resultId);
    }

    @PostMapping
    public Answer saveAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @PutMapping("/{id}/correct/{correct}")
    public Answer updateCorrect(@PathVariable String id, @PathVariable Boolean correct) {
        return answerService.setCorrect(id, correct);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable String id) {
        answerService.deleteAnswer(id);
    }

}
