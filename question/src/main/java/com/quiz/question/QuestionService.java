package com.quiz.question;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    public Question getQuestionById(String id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getAllQuestionsForQuiz(Integer quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        // provjeriti da li je kviz vec radjen
        return saveQuestion(question);
    }

    public void deleteQuestion(String id) {
        // provjera da li je kviz vec radjen
        // brisanje svih ponudjenih odgovora za pitanje
        questionRepository.deleteById(id);
    }

    public Long deleteAllQuestionsForQuiz(Integer quizId) {
        log.info("obrisane sva pitanja za kviz");
        return questionRepository.deleteByQuizId(quizId);
    }
}
