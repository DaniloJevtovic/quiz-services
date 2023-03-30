package com.quiz.useranswer;

import com.quiz.clients.choice.ChoiceClient;
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
    private final ChoiceClient choiceClient;

    public Page<Answer> getAll(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public Answer getAnswerById(String id) {
        return answerRepository.findById(id).orElse(null);
    }

    public List<Answer> getAnswersForResultAndQuestion(Integer resId, String questionId) {
        return answerRepository.findByQuizResultIdAndQuestionId(resId, questionId);
    }

    public List<Answer> getAnswersForResult(Integer resultId) {
        return answerRepository.findByQuizResultId(resultId);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    // metoda racuna ukupan rezultat za citav kviz (rezultat)
    public Double calculateScoreForSolvedQuiz(Integer resultId) {
        checkChosenAnswersForResult(resultId);  // provjera selektovanih odgovora
        return getAnswersForResult(resultId).stream().mapToDouble(a -> a.getPoints()).sum();
    }

    // racuna rezultat za pitanje
    public Double calculateScoreForQuestion(Integer resId, String questionId) {
        return getAnswersForResultAndQuestion(resId, questionId)
                .stream().mapToDouble(ans -> ans.getPoints()).sum();
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

    // lista odgovora za rezultat po tipu
    public List<Answer> getAnswersForSolvedQuizByType(Integer resId, AnswerType type) {
        return answerRepository.findByQuizResultIdAndType(resId, type);
    }

    // provjerara selektovanih odgovora (po zavrsetku kviza!)
    public Double checkChosenAnswersForResult(Integer resId) {
        List<Answer> chosenAnswers = getAnswersForSolvedQuizByType(resId, AnswerType.CHOICE);
        double score = 0.0;

        // proci kroz sve IZABRANE odgovore i provjeriti da li su tacni
        for (Answer ans : chosenAnswers) {
            // poziv choice servisa za provjeru tacnosti odgovora
            ans.setCorrect(choiceClient.isCorrect(ans.getAnswer()));

            // za sad samo pozitivni poeni
            if (ans.getCorrect()) score += ans.getPoints();
            else ans.setPoints(0.0);    // ako nije tacan postavi poene na 0

            // update sacuvanog odgovora (tacno/netacno)
            answerRepository.save(ans);
        }

        return score;
    }

    public void deleteAnswer(String answerId) {
        answerRepository.deleteById(answerId);
    }

    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }
}
