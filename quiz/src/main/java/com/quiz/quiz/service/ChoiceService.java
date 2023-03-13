package com.quiz.quiz.service;

import com.quiz.quiz.dto.ChoiceReqDTO;
import com.quiz.quiz.model.Choice;
import com.quiz.quiz.repository.ChoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final QuestionService questionService;

    public Page<Choice> getAllChoices(Pageable pageable) {
        return choiceRepository.findAll(pageable);
    }

    public List<Choice> getAllChoicesForQuestion(Integer questionId) {
        return choiceRepository.findByQuestionId(questionId);
    }

    public List<Choice> getCorrectChoicesForQuestion(Integer questionId) {
        return choiceRepository.findByQuestionIdAndCorrectTrue(questionId);
    }

    public Choice getChoiceById(Integer id) {
        return choiceRepository.findById(id).orElse(null);
    }

    public Choice saveChoice(ChoiceReqDTO dto) {
        Choice choice = new Choice();
        choice.setChoice(dto.choice());
        choice.setCorrect(dto.correct());
        choice.setQuestion(questionService.getQuestionById(dto.questionId()));

        return choiceRepository.save(choice);
    }

    public Choice updateChoice(Integer choiceId, ChoiceReqDTO dto) {
        // provjera da li je kviz vec radjen
        Choice choice = getChoiceById(choiceId);
        choice.setChoice(dto.choice());
        choice.setCorrect(dto.correct());

        return choiceRepository.save(choice);
    }

    public void deleteChoice(Integer id) {
        choiceRepository.deleteById(id);
    }
}
