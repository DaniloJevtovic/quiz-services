package com.quiz.useranswer;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Page<Answer> getAll(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public Answer getAnswerById(String id) {
        return answerRepository.findById(id).orElse(null);
    }

    public List<Answer> getAnswersForQuestionAndSolver(String questionId, Integer solverId) {
        return answerRepository.findByQuestionIdAndSolverId(questionId, solverId);
    }

    public List<Answer> getAnswersForQuestionSolverAndType(String questionId, Integer solverId, AnswerType type) {
        return answerRepository.findByQuestionIdAndSolverIdAndType(questionId, solverId, type);
    }

    public List<Answer> getAnswersForResult(Integer resultId) {
        return answerRepository.findByQuizResultId(resultId);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    // metoda racuna ukupan rezultat za citav kviz (rezultat)
    // moze kad se pritisne dugme finish ili istekne vrijeme
    public Double calculateScoreForSolvedQuiz(Integer resultId) {
        // pozvati servis za update rezultata
        return getAnswersForResult(resultId).stream().mapToDouble(a -> a.getPoints()).sum();
    }

    // racuna rezultat za pitanje
    public Double calculateScoreForQuestion(String questionId, Integer solverId) {
        return getAnswersForQuestionAndSolver(questionId, solverId)
                .stream().mapToDouble(ans -> ans.getPoints()).sum();
    }

    // vlasnik kviza oznacava da li je odgovor tacan ili netacan
    public Answer setCorrect(SetAnsCorrectDTO dto) {
        Answer answer = getAnswerById(dto.answerId());
        answer.setCorrect(dto.correct());
        answer.setPoints(dto.points());
        return answerRepository.save(answer);
    }

    public void deleteAnswer(String answerId) {
        answerRepository.deleteById(answerId);
    }
}
