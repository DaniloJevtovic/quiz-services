package com.quiz.clients.choice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "choice", url = "localhost:8085/api/choices")
//@FeignClient(name = "choice")
public interface ChoiceClient {

    @GetMapping("/checkChoice/{id}")
    public Boolean isCorrect(@PathVariable("id") String id);
}
