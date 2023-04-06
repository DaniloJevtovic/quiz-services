package com.quiz.clients.answer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "answer", url = "localhost:8087/api/answers")
public interface AnswerClient {

    @GetMapping("/solvedQuiz/{resultId}/calculateScore")
    public Double calculateScoreForSolvedQuiz(@PathVariable("resultId") Integer resultId);
}
