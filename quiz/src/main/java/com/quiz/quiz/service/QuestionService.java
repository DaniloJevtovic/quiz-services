package com.quiz.quiz.service;

import com.quiz.quiz.dto.QuestionReqDTO;
import com.quiz.quiz.model.Question;
import com.quiz.quiz.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizService quizService;

    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getAllQuestionsForQuiz(Integer quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public Question saveQuestion(QuestionReqDTO dto) {
        Question question = new Question();
        question.setQuestion(dto.question());
        question.setQuiz(quizService.getQuizById(dto.quizId()));
        question.setType(dto.type());

        return questionRepository.save(question);
    }

    public Question updateQuestion(Integer id, QuestionReqDTO dto) {
        // provjeriti da li je kviz vec radjen
        Question question = getQuestionById(id);
        question.setQuestion(dto.question());
        question.setType(dto.type());

        return questionRepository.save(question);
    }

    public void deleteQuestion(Integer id) {
        // provjera da li je kviz vec radjen
        questionRepository.deleteById(id);
    }
}
