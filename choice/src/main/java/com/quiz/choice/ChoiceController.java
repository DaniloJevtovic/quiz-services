package com.quiz.choice;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/choices")
@AllArgsConstructor
public class ChoiceController {

    private final ChoiceService choiceService;

    @GetMapping
    public Page<Choice> getAll(Pageable pageable) {
        return choiceService.getAllChoices(pageable);
    }

    @GetMapping("/{id}")
    public Choice getChoiceById(@PathVariable String id) {
        return choiceService.getChoiceById(id);
    }

    @GetMapping("/checkChoice/{id}")
    public Boolean checkChoice(@PathVariable String id) {
        return choiceService.checkChoice(id);
    }

    @GetMapping("/question/{questionId}")
    public List<Choice> getAllChoicesForQuestion(@PathVariable String questionId) {
        return choiceService.getAllChoicesForQuestion(questionId);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Choice> getAllChoicesForQuiz(@PathVariable Integer quizId) {
        return choiceService.getAllChoicesForQuiz(quizId);
    }

    @GetMapping("/question/{questionId}/correct")
    public List<Choice> getCorrectChoicesForQuestion(@PathVariable String questionId) {
        return choiceService.getCorrectChoicesForQuestion(questionId);
    }

    @PostMapping
    public Choice saveChoice(@RequestBody Choice choice) {
        return choiceService.saveChoice(choice);
    }

    @PutMapping
    public Choice updateChoice(@RequestBody Choice choice) {
        return choiceService.updateChoice(choice);
    }

    @DeleteMapping("/{id}")
    public void deleteChoice(@PathVariable String id) {
        choiceService.deleteChoice(id);
    }

    @DeleteMapping("/question/{questionId}")
    public void deleteAllChoicesForQuestion(@PathVariable String questionId) {
        choiceService.deleteAllCoicesForQuestion(questionId);
    }

    @DeleteMapping("/quiz/{quizId}")
    public void deleteAllChoicesForQuiz(@PathVariable Integer quizId) {
        choiceService.deleteAlChoicesForQuz(quizId);
    }
}
