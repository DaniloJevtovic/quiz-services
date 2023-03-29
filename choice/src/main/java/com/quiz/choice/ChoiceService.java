package com.quiz.choice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChoiceService {

    private final ChoiceRepository choiceRepository;

    public Page<Choice> getAllChoices(Pageable pageable) {
        return choiceRepository.findAll(pageable);
    }

    public List<Choice> getAllChoicesForQuestion(String questionId) {
        return choiceRepository.findByQuestionId(questionId);
    }

    public List<Choice> getAllChoicesForQuiz(Integer guizId) {
        return choiceRepository.findByQuizId(guizId);
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

    // provjera da li je izbor tacan ili ne -- kad se zavrsi kviz
    // moze i poseban servis da se napravi za ovu provjeru(choiceId, correct)
    public boolean checkChoice(String choiceId) {
        return getChoiceById(choiceId).getCorrect();
    }

    public void deleteChoice(String id) {
        choiceRepository.deleteById(id);
    }

    public Long deleteAllCoicesForQuestion(String questionId) {
        log.info("obrisani ponudjeni odgovori za pitanje");
        return choiceRepository.deleteByQuestionId(questionId);
    }

    public Long deleteAlChoicesForQuz(Integer quizId) {
        log.info("obrisani ponudjeni odgovori za kviz");
        return choiceRepository.deleteByQuizId(quizId);
    }

}
