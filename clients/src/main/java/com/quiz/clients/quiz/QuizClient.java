package com.quiz.clients.quiz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "quiz", url = "localhost:8083/api/quizzies")
//@FeignClient("quiz")
public interface QuizClient {

    @GetMapping(path = "/{quizId}")
    public QuizDTO getQuizById(@PathVariable("quizId") Integer id);

    // provjera da li je kviz radjen - iz quiz modula
    @GetMapping("/checkSolved/{quizId}")
    public Boolean checkSolver(@PathVariable("quizId") Integer quizId);

    // provjera da li je kviz radjen - takequiz modul
    @GetMapping("/{id}/checkSolved")
    public Boolean checkQuizSolved(@PathVariable("id") Integer id);

    // provjera da li postoje kvizovi u kategoriji
    @GetMapping("/category/{categoryId}/check")
    public Boolean checkQuizzesInCategory(@PathVariable("categoryId") Integer categoryId);
}
