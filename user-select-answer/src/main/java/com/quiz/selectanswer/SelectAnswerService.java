package com.quiz.selectanswer;

import com.quiz.clients.choice.ChoiceClient;
import com.quiz.clients.result.UpdatePointsDTO;
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
public class SelectAnswerService {

    private final SelectAnswerRepository selectAnswerRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final ChoiceClient choiceClient;

    public Page<SelectAnswer> getAll(Pageable pageable) {
        return selectAnswerRepository.findAll(pageable);
    }

    public SelectAnswer getAnswerById(String id) {
        return selectAnswerRepository.findById(id).orElse(null);
    }

    public SelectAnswer getAnswerForResultAndChoice(Integer resultId, String choiceId) {
        return selectAnswerRepository.findByQuizResultIdAndChoiceId(resultId, choiceId);
    }

    public List<SelectAnswer> getSelectedAnswersForResult(Integer resId) {
        return selectAnswerRepository.findByQuizResultId(resId);
    }

    public List<SelectAnswer> getSelectedAnswersForQuestion(String questionId) {
        return selectAnswerRepository.findByQuestionId(questionId);
    }

    public SelectAnswer selectAnswer(SelectAnswer answer) {
        return selectAnswerRepository.save(answer);
    }

    // rucno oznacavanje da li je odgovor tacan/netacan
    public SelectAnswer setCorrect(SetAnsCorrectDTO dto) {
        SelectAnswer selectedAnswer = getAnswerById(dto.answerId());
        selectedAnswer.setCorrect(dto.correct());
        selectedAnswer.setPoints(dto.points());

        // poziv servisa za update rezultata
        rabbitMQMessageProducer.publish(
                new UpdatePointsDTO(selectedAnswer.getQuizResultId(), dto.points()),
                "results.exchange",
                "results.routing-key"
        );

        return selectAnswerRepository.save(selectedAnswer);
    }

    // metoda racuna ukupan rezultat za citav kviz (rezultat)
    public Double calculateScoreForSolvedQuiz(Integer resultId) {
        return getSelectedAnswersForResult(resultId).stream().mapToDouble(a -> a.getPoints()).sum();
    }

    // provjerara selektovanih odgovora (po zavrsetku kviza!)
    public Double checkChosenAnswersForResult(Integer resId) {
        List<SelectAnswer> selectedAnswers = getSelectedAnswersForResult(resId);
        double score = 0.0;

        // proci kroz sve slektovane odgovore i provjeriti da li su tacni
        for (SelectAnswer ans : selectedAnswers) {
            // poziv choice servisa za provjeru tacnosti odgovora
            ans.setCorrect(choiceClient.isCorrect(ans.getChoiceId()));

            // za sad samo pozitivni poeni
            if (ans.getCorrect()) score += ans.getPoints();
            else ans.setPoints(0.0);    // ako nije tacan postavi poene na 0

            // update sacuvanog odgovora (tacno/netacno)
            selectAnswerRepository.save(ans);
        }

        return score;
    }

    // racuna rezultat za pitanje
    public Double calculateScoreForQuestion(String questionId) {
        return getSelectedAnswersForQuestion(questionId).stream().mapToDouble(ans -> ans.getPoints()).sum();
    }

    public void deleteSelectedAnswer(String ansId) {
        selectAnswerRepository.deleteById(ansId);
    }

    public void deleteAll() {
        selectAnswerRepository.deleteAll();
    }
}
