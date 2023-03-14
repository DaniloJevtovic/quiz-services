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

    public List<Answer> getAnswersForQuestionAndSolver(Integer questionId, Integer solverId) {
        return answerRepository.findByQuestionIdAndSolverId(questionId, solverId);
    }

    public List<Answer> getAnswersForResult(Integer resultId) {
        return answerRepository.findByQuizResultId(resultId);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    // vlasnik kviza oznacava da li je odgovor tacan ili netacan
    public Answer setCorrect(String answerId, Boolean correct) {
        Answer answer = getAnswerById(answerId);
        answer.setCorrect(correct);
        return answerRepository.save(answer);
    }

    public void deleteAnswer(String answerId) {
        answerRepository.deleteById(answerId);
    }
}
