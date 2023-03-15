package com.quiz.choice;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    public Page<Choice> getAllChoices(Pageable pageable) {
        return choiceRepository.findAll(pageable);
    }

    public List<Choice> getAllChoicesForQuestion(String questionId) {
        return choiceRepository.findByQuestionId(questionId);
    }

    public List<Choice> getCorrectChoicesForQuestion(String questionId) {
        return choiceRepository.findByQuestionIdAndCorrectTrue(questionId);
    }

    public Choice getChoiceById(String id) {
        return choiceRepository.findById(id).orElse(null);
    }

    public Choice saveChoice(Choice choice) {
        return choiceRepository.save(choice);
    }

    public Choice updateChoice(Choice choice) {
        // provjera da li je kviz vec radjen
        return choiceRepository.save(choice);
    }

    public void deleteChoice(String id) {
        choiceRepository.deleteById(id);
    }

    public Long deleteAllCoicesForQuestion(String questionId) {
        return deleteAllCoicesForQuestion(questionId);
    }


}
