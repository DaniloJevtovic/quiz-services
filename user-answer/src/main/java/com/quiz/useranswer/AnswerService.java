package com.quiz.useranswer;

import com.quiz.rabbitmq.RabbitMQMessageProducer;
import com.quiz.clients.result.UpdatePointsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Page<Answer> getAll(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public Answer getAnswerById(String id) {
        return answerRepository.findById(id).orElse(null);
    }

    public List<Answer> getAnswersForResult(Integer resultId) {
        return answerRepository.findByQuizResultId(resultId);
    }

    public List<Answer> getAnswersForQuestion(String questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    // metoda racuna ukupan rezultat za citav kviz (rezultat)
    public Double calculateScoreForSolvedQuiz(Integer resultId) {
        return getAnswersForResult(resultId).stream().mapToDouble(a -> a.getPoints()).sum();
    }

    // racuna rezultat za pitanje
    public Double calculateScoreForQuestion(String questionId) {
        return getAnswersForQuestion(questionId).stream().mapToDouble(ans -> ans.getPoints()).sum();
    }

    // vlasnik kviza oznacava da li je odgovor tacan ili netacan - rucno
    public Answer setCorrect(SetAnsCorrectDTO dto) {
        Answer answer = getAnswerById(dto.answerId());
        answer.setCorrect(dto.correct());
        answer.setPoints(dto.points());

        // poziv servisa za update rezultata
        rabbitMQMessageProducer.publish(
                new UpdatePointsDTO(answer.getQuizResultId(), dto.points()),
                "results.exchange",
                "results.routing-key"
        );

        return answerRepository.save(answer);
    }

    public void deleteAnswer(String answerId) {
        answerRepository.deleteById(answerId);
    }

    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }
}
