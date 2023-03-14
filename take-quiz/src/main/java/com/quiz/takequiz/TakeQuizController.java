package com.quiz.takequiz;

import com.quiz.takequiz.dto.TakeQuizReqDTO;
import com.quiz.takequiz.dto.UpdateSolvedQuiz;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/take-quiz")
@AllArgsConstructor
public class TakeQuizController {

    private final TakeQuizService takeQuizService;

    @GetMapping
    public Page<TakeQuiz> getAllSolvedQuizies(Pageable pageable) {
        return takeQuizService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public TakeQuiz getSolvedQuizById(@PathVariable Integer id) {
        return takeQuizService.getSolvedQuizById(id);
    }

    @GetMapping("/user/{userId}")
    public Page<TakeQuiz> getSolvedQuiziesForUser(@PathVariable Integer userId, Pageable pageable) {
        return takeQuizService.getSolvedQuiziesForUser(userId, pageable);
    }

    @GetMapping("/quiz/{quizId}")
    public Page<TakeQuiz> getAllSolvedQuiziesForQuiz(@PathVariable Integer quizId, Pageable pageable) {
        return takeQuizService.getSolvedQuiziesForQuiz(quizId, pageable);
    }

    @PostMapping
    public TakeQuiz takeQuiz(@RequestBody TakeQuizReqDTO dto) {
        return takeQuizService.takeQuiz(dto);
    }

    @PutMapping("/{id}")
    public TakeQuiz updateSolveQuiz(@PathVariable Integer id, @RequestBody UpdateSolvedQuiz dto) {
        return takeQuizService.updateSolvedQuiz(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSolvedQuiz(@PathVariable Integer id) {
        takeQuizService.deleteSolvedQuiz(id);
    }

}
