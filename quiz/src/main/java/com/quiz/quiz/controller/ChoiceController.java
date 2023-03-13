package com.quiz.quiz.controller;

import com.quiz.quiz.dto.ChoiceReqDTO;
import com.quiz.quiz.model.Choice;
import com.quiz.quiz.service.ChoiceService;
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
    public Choice getChoiceById(@PathVariable Integer id) {
        return choiceService.getChoiceById(id);
    }

    @GetMapping("/question/{questionId}")
    public List<Choice> getAllChoicesForQuestion(@PathVariable Integer questionId) {
        return choiceService.getAllChoicesForQuestion(questionId);
    }

    @GetMapping("/question/{questionId}/correct")
    public List<Choice> getCorrectChoicesForQuestion(@PathVariable Integer questionId) {
        return choiceService.getCorrectChoicesForQuestion(questionId);
    }

    @PostMapping
    public Choice saveChoice(@RequestBody ChoiceReqDTO dto) {
        return choiceService.saveChoice(dto);
    }

    @PutMapping("/{id}")
    public Choice updateChoice(@PathVariable Integer id, @RequestBody ChoiceReqDTO dto) {
        return choiceService.updateChoice(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteChoice(@PathVariable Integer id) {
        choiceService.deleteChoice(id);
    }
}
