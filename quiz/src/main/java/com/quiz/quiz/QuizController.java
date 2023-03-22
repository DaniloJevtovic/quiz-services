package com.quiz.quiz;

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
        return quizService.getQuizById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Quiz> getAllQuizziesForUser(@PathVariable Integer userId) {
        return quizService.getQuizziesForUser(userId);
    }

    @GetMapping("/checkSolved/{quizId}")
    public Boolean checkSolved(@PathVariable Integer quizId) {
        return quizService.checkQuizSolved(quizId);
    }

    @GetMapping("/category/{catId}/status/{status}")
    public Page<Quiz> getQuizziesForCategory(@PathVariable Integer catId, @PathVariable QuizStatus status,
                                             Pageable pageable) {
        return quizService.getQuizziesForCategoryAndStatus(catId, status, pageable);
    }

    @GetMapping("/search/category/{catId}/name/{name}")
    public List<Quiz> searchAllQuizzesInCategory(@PathVariable Integer catId, @PathVariable String name) {
        return quizService.searchAllQuizzesByNameForCategory(catId, name);
    }

    @GetMapping("/search/category/{catId}/name/{name}/status")
    public List<Quiz> searchAllQuizzesInCategoryByStatus(@PathVariable Integer catId, @PathVariable QuizStatus status,
                                                         @PathVariable String name) {
        return quizService.searchQuizzesByNameCategoryAndStatus(catId, status, name);
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody QuizReqDTO dto) {
        return quizService.createQuiz(dto);
    }

    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Integer id, @RequestBody QuizReqDTO dto) {
        return quizService.updateQuiz(id, dto);
    }

    @PatchMapping("/{id}/status/{status}")
    public Quiz changeStatus(@PathVariable Integer id, @PathVariable QuizStatus status) {
        return quizService.changeQuizStatus(id, status);
    }

    @PatchMapping("/{id}/increase-num-of-solves")
    public Integer increaseNumOfSolver(@PathVariable Integer id) {
        return quizService.increaseNumOfSolves(id);
    }

    @PatchMapping("/{id}/approve")
    public Quiz approveQuiz(@PathVariable Integer id) {
        return quizService.approveQuiz(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Integer id) {
        quizService.deleteQuiz(id);
    }

    @DeleteMapping("/category/{categoryId}")
    public Double deleteAllQuiziesForCategory(@PathVariable Integer categoryId) {
        return quizService.deleteAllQuizesForCategory(categoryId);
    }
}
