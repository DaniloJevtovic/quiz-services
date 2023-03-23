package com.quiz.question;

import com.quiz.rabbitmq.RabbitMQMessageProducer;
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
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

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
        // provjeriti da li je kviz vec radjen - to moze na frontend-u
        return saveQuestion(question);
    }

    public void deleteQuestion(String id) {
        // brisanje svih ponudjenih odgovora za pitanje
        rabbitMQMessageProducer.publish(id, "del-question.exchange", "del-question.routing-key");
        questionRepository.deleteById(id);
    }

    public Long deleteAllQuestionsForQuiz(Integer quizId) {
        log.info("obrisana sva pitanja za kviz");
        return questionRepository.deleteByQuizId(quizId);
    }
}
